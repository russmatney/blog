(ns blog.migrate
  (:require
   [clojure.string :as string]
   [babashka.fs :as fs]
   [blog.dates :as dates]
   [blog.config :as config]
   [clojure.edn :as edn]))

(defn parse-existing-frontmatter [content]
  "Extract existing frontmatter from markdown file."
  (when (string/starts-with? content "---")
    (let [parts (string/split content #"---" 3)]
      (when (>= (count parts) 3)
        (try
          {:frontmatter-raw (second parts)
           :body (last parts)}
          (catch Exception e
            (println "Error parsing frontmatter:" (.getMessage e))
            nil))))))

(defn parse-yaml-simple [yaml-str]
  "Simple YAML parser for frontmatter (handles common cases)."
  (let [lines (string/split-lines yaml-str)
        result (reduce
                (fn [acc line]
                  (let [line (string/trim line)]
                    (cond
                      (empty? line) acc
                      (string/starts-with? line "#") acc ; comment
                      (string/starts-with? line "-") ; array item
                      (let [value (string/trim (subs line 1))]
                        (update acc :_current-array (fnil conj []) value))
                      (string/includes? line ":")
                      (let [[k v] (string/split line #":" 2)
                            k (keyword (string/trim k))
                            v (when v (string/trim v))
                            v (cond
                                ;; inline array: [a, b, c]
                                (and v (string/starts-with? v "["))
                                (vec (remove string/blank?
                                             (mapv string/trim
                                                   (string/split
                                                     (string/replace v #"[\[\]]" "")
                                                     #","))))
                                ;; empty value, check if next lines are array
                                (or (nil? v) (string/blank? v))
                                (if-let [arr (:_current-array acc)]
                                  arr
                                  v)
                                :else v)]
                        (-> acc
                            (assoc k v)
                            (dissoc :_current-array)))
                      :else acc)))
                {}
                lines)]
    result))

(defn extract-frontmatter-data [fm-raw]
  "Extract structured data from frontmatter string."
  (when fm-raw
    (let [parsed (parse-yaml-simple fm-raw)]
      {:title (or (:title parsed) (when (:Title parsed) (:Title parsed)))
       :date (or (:date parsed) (when (:Date parsed) (:Date parsed)))
       :tags (or (:tags parsed)
                 (:Tags parsed)
                 (when-let [cats (:categories parsed)]
                   (if (vector? cats) cats [cats]))
                 [])})))

(defn filename->category [path]
  "Extract category from parent directory."
  (let [parent (fs/file-name (fs/parent path))
        parent-str (str parent)]
    (cond
      (= parent-str "100-worders") "hundos"
      (= parent-str "docs") "general"
      :else parent-str)))

(defn infer-tags [category fname]
  "Infer tags from category and filename."
  (cond
    (= category "techsposure") ["tech"]
    (= category "hundos") ["hundos" "writing"]
    (= category "getitwrite") ["writing"]
    (= category "groks") ["learning"]
    (= category "devlogs") ["devlog"]
    (string/includes? fname "godot") ["godot" "gamedev"]
    (string/includes? fname "clojure") ["clojure"]
    :else []))

(defn format-tags [tags]
  "Format tags array as YAML."
  (let [clean-tags (vec (remove string/blank? tags))]
    (if (empty? clean-tags)
      ""
      (str "tags: [" (string/join ", " clean-tags) "]\n"))))

(defn migrate-post-frontmatter [path]
  "Normalize frontmatter for a single post."
  (let [path-str (str path)
        content (slurp path-str)
        fname (fs/file-name path)
        {:keys [title date]} (dates/parse-datey-fname fname)
        category (filename->category path)

        existing-fm (parse-existing-frontmatter content)
        existing-data (when existing-fm
                        (extract-frontmatter-data (:frontmatter-raw existing-fm)))

        ;; Prefer existing frontmatter, fall back to extracted
        final-title (or (:title existing-data) title (string/replace fname #"\.md$" ""))
        final-date (or (:date existing-data) (when date (str date)))

        existing-tags (when existing-data
                        (if (vector? (:tags existing-data))
                          (:tags existing-data)
                          (when (:tags existing-data) [(:tags existing-data)])))
        inferred-tags (infer-tags category fname)
        final-tags (vec (distinct (concat existing-tags inferred-tags)))

        new-frontmatter (str "---\n"
                            "title: \"" (string/replace final-title #"\"" "\\\"") "\"\n"
                            (when final-date
                              (str "date: " final-date "\n"))
                            "category: " category "\n"
                            (format-tags final-tags)
                            "---\n")

        new-content (str new-frontmatter
                        (if existing-fm
                          (:body existing-fm)
                          content))]

    ;; Write back to file
    (spit path-str new-content)
    (println "✓ Migrated:" fname "(" category ")")))

(defn migrate-all-posts []
  "Migrate all posts in docs/ to have normalized frontmatter."
  (let [dirs [(str config/posts-dir)
              (str config/devlogs-dir)
              (str config/portfolio-dir)]
        all-files (mapcat #(fs/glob % "**/*.md") dirs)
        posts (filter
                #(and
                   (not (string/includes? (str %) "README.md"))
                   (not (string/includes? (str %) "_sidebar.md"))
                   (not (string/includes? (str %) "_navbar.md"))
                   (not (string/includes? (str %) "_404.md"))
                   (not (string/includes? (str %) "_coverpage.md")))
                all-files)]

    (println "\n=== Migrating" (count posts) "posts ===\n")
    (doseq [post posts]
      (try
        (migrate-post-frontmatter post)
        (catch Exception e
          (println "ERROR migrating" (str post) ":" (.getMessage e)))))
    (println "\n=== Migration complete ===\n")))

(comment
  ;; Test on a single file
  (migrate-post-frontmatter
    (str config/posts-dir "/techsposure/2020-04-23-reitit-router-in-reframe.md"))

  ;; Run full migration
  (migrate-all-posts))
