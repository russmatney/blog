(ns blog.fix-duplicate-titles
  (:require
   [clojure.string :as string]
   [babashka.fs :as fs]
   [blog.config :as config]
   [blog.migrate :as migrate]))

(def content-base "src/content/docs")

(defn normalize-text [text]
  "Normalize text for fuzzy matching (lowercase, alphanumeric only)."
  (-> text
      (string/lower-case)
      (string/replace #"[^a-z0-9]+" "")))

(defn extract-first-h1 [body]
  "Find the first H1 heading in markdown body."
  (when-let [match (re-find #"(?m)^#\s+(.+?)$" body)]
    {:full-match (first match)
     :heading-text (second match)
     :line-match (first match)}))

(defn remove-first-h1 [body h1-match]
  "Remove the first H1 heading from body."
  (string/replace-first body (str h1-match "\n") ""))

(defn fix-duplicate-title [file-path dry-run?]
  "Remove duplicate H1 if it matches frontmatter title."
  (let [file-path-str (str file-path)
        content (slurp file-path-str)
        parsed (migrate/parse-existing-frontmatter content)]

    (when parsed
      (let [frontmatter-data (migrate/extract-frontmatter-data (:frontmatter-raw parsed))
            title (:title frontmatter-data)
            body (:body parsed)
            h1-info (extract-first-h1 body)]

        (when (and title h1-info)
          (let [title-norm (normalize-text title)
                h1-norm (normalize-text (:heading-text h1-info))
                is-duplicate? (= title-norm h1-norm)]

            (if is-duplicate?
              (do
                (println "\n[DUPLICATE]" (fs/file-name file-path))
                (println "  Title:" title)
                (println "  H1:   " (:heading-text h1-info))

                (if dry-run?
                  (println "  [DRY RUN] Would remove duplicate H1")
                  (let [new-body (remove-first-h1 body (:line-match h1-info))
                        new-content (str "---\n" (:frontmatter-raw parsed) "---\n" new-body)]
                    (spit file-path-str new-content)
                    (println "  [REMOVED] Duplicate H1"))))

              (do
                (println "\n[MISMATCH]" (fs/file-name file-path))
                (println "  Title:" title)
                (println "  H1:   " (:heading-text h1-info))
                (println "  [REVIEW] Manual review needed - titles differ")))))))))

(defn find-files-with-h1 []
  "Find all content files with H1 headings."
  (let [content-dir (str config/repo-dir "/" content-base)
        all-files (fs/glob content-dir "**/*.{md,mdx}")
        files-with-h1 (filter
                       (fn [f]
                         (let [content (slurp (str f))]
                           (re-find #"(?m)^#\s+" content)))
                       all-files)]
    files-with-h1))

(defn fix-all-titles [& args]
  "Fix duplicate H1 headings in all content files."
  (let [dry-run? (some #{"--dry-run"} args)
        files (find-files-with-h1)]

    (println "\n=== Duplicate Title Fixer ===")
    (println "Found" (count files) "files with H1 headings")
    (when dry-run?
      (println "[DRY RUN MODE] No files will be modified\n"))

    (let [stats (atom {:duplicates 0 :mismatches 0 :no-title 0})]
      (doseq [file files]
        (try
          (fix-duplicate-title file dry-run?)
          (catch Exception e
            (println "[ERROR]" (str file) "-" (.getMessage e)))))

      (println "\n===" (if dry-run? "Dry run" "Fix") "complete ===\n"))))

;; CLI entry point
(apply fix-all-titles *command-line-args*)
