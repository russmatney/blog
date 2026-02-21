(ns blog.fix-assets
  (:require
   [clojure.string :as string]
   [babashka.fs :as fs]
   [blog.config :as config]))

(def content-base "src/content/docs")
(def assets-base "src/assets")

(defn calculate-depth [file-path]
  "Calculate directory depth from content base."
  (let [rel-path (string/replace file-path (str config/repo-dir "/" content-base "/") "")
        parts (fs/components (fs/parent rel-path))]
    (count parts)))

(defn relative-path-to-assets [depth]
  "Generate relative path from content file to assets directory."
  (let [ups (apply str (repeat (inc depth) "../"))]
    (str ups "assets/")))

(defn find-asset-references [content]
  "Find all asset references in markdown content."
  (let [patterns [#"src=\"_images/([^\"]+)\""
                  #"src='_images/([^']+)'"
                  #"!\[([^\]]*)\]\(_images/([^\)]+)\)"]
        matches (atom [])]
    (doseq [pattern patterns]
      (let [found (re-seq pattern content)]
        (when found
          (swap! matches concat found))))
    @matches))

(defn validate-asset-exists [asset-path]
  "Check if referenced asset exists in assets directory."
  (let [full-path (str config/repo-dir "/" assets-base "/" asset-path)]
    (fs/exists? full-path)))

(defn fix-asset-paths-in-content [content depth]
  "Replace all _images/ references with correct relative path."
  (let [replacement (str (relative-path-to-assets depth) "_images/")]
    (-> content
        (string/replace #"src=\"_images/" (str "src=\"" replacement))
        (string/replace #"src='_images/" (str "src='" replacement))
        (string/replace #"!\[([^\]]*)\]\(_images/" (str "![$1](" replacement)))))

(defn fix-file-assets [file-path dry-run?]
  "Fix asset paths in a single file."
  (let [file-path-str (str file-path)
        content (slurp file-path-str)
        depth (calculate-depth file-path-str)
        refs (find-asset-references content)]

    (when (seq refs)
      (println "\n[FILE]" (fs/file-name file-path) "(depth:" depth ")")

      ;; Validate all assets exist
      (doseq [ref refs]
        (let [asset-path (if (string/starts-with? (str (first ref)) "!")
                          (last ref)  ; markdown image syntax
                          (second ref)) ; html img tag
              full-ref (str "_images/" asset-path)
              exists? (validate-asset-exists full-ref)]
          (if exists?
            (println "  [OK]" full-ref)
            (println "  [MISSING]" full-ref))))

      ;; Apply fixes
      (let [new-content (fix-asset-paths-in-content content depth)]
        (if dry-run?
          (println "  [DRY RUN] Would update" (count refs) "reference(s)")
          (do
            (spit file-path-str new-content)
            (println "  [FIXED]" (count refs) "reference(s)")))))))

(defn find-files-with-asset-refs []
  "Find all content files with _images/ references."
  (let [content-dir (str config/repo-dir "/" content-base)
        all-files (fs/glob content-dir "**/*.{md,mdx}")
        files-with-refs (filter
                         (fn [f]
                           (let [content (slurp (str f))]
                             (or (string/includes? content "_images/")
                                 (string/includes? content "src=\"_images")
                                 (string/includes? content "src='_images"))))
                         all-files)]
    files-with-refs))

(defn fix-all-assets [& args]
  "Fix asset paths in all content files."
  (let [dry-run? (some #{"--dry-run"} args)
        files (find-files-with-asset-refs)]

    (println "\n=== Asset Path Fixer ===")
    (println "Found" (count files) "files with _images/ references")
    (when dry-run?
      (println "[DRY RUN MODE] No files will be modified\n"))

    (doseq [file files]
      (try
        (fix-file-assets file dry-run?)
        (catch Exception e
          (println "[ERROR]" (str file) "-" (.getMessage e)))))

    (println "\n===" (if dry-run? "Dry run" "Fix") "complete ===\n")))

;; CLI entry point
(apply fix-all-assets *command-line-args*)
