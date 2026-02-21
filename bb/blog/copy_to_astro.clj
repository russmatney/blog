(ns blog.copy-to-astro
  (:require
   [babashka.fs :as fs]
   [clojure.string :as string]
   [blog.config :as config]))

(def src-content-dir (str config/repo-dir "/src/content/docs"))

(defn should-copy? [path]
  "Determine if file should be copied to Astro."
  (let [fname (fs/file-name path)
        path-str (str path)]
    (and
      ;; Must be a markdown file
      (string/ends-with? fname ".md")
      ;; Exclude auto-generated files
      (not (= fname "README.md"))
      (not (= fname "_sidebar.md"))
      (not (= fname "_navbar.md"))
      ;; Exclude special Docsify files
      (not (= fname "_404.md"))
      (not (= fname "_coverpage.md"))
      ;; Exclude node_modules or hidden directories
      (not (string/includes? path-str "node_modules"))
      (not (string/includes? path-str "/."))
      ;; Exclude old archived content
      (not (string/includes? path-str "/docs.old")))))

(defn copy-post [src-path]
  "Copy a post from docs/ to src/content/docs/."
  (let [relative (fs/relativize config/docs-dir src-path)
        dest-path (str src-content-dir "/" relative)
        dest-dir (fs/parent dest-path)]
    (fs/create-dirs dest-dir)
    (fs/copy src-path dest-path {:replace-existing true})
    (println "✓ Copied:" relative)))

(defn copy-all-content []
  "Copy all content from docs/ to src/content/docs/."
  (let [all-files (fs/glob (str config/docs-dir) "**/*.md")
        files-to-copy (filter should-copy? all-files)]

    (println "\n=== Copying" (count files-to-copy) "files ===\n")
    (doseq [f files-to-copy]
      (try
        (copy-post f)
        (catch Exception e
          (println "ERROR copying" (str f) ":" (.getMessage e)))))
    (println "\n=== Copy complete ===\n")))

(defn copy-assets []
  "Copy static assets (images, html, CNAME)."
  (println "\n=== Copying static assets ===\n")

  ;; Copy images
  (when (fs/exists? (str config/docs-dir "/_images"))
    (println "Copying images...")
    (fs/copy-tree
      (str config/docs-dir "/_images")
      (str config/repo-dir "/src/assets/_images")
      {:replace-existing true})
    (println "✓ Images copied"))

  ;; Copy reveal.js presentations
  (when (fs/exists? (str config/docs-dir "/html"))
    (println "Copying reveal.js presentations...")
    (fs/copy-tree
      (str config/docs-dir "/html")
      (str config/repo-dir "/public/html")
      {:replace-existing true})
    (println "✓ HTML presentations copied"))

  ;; Copy CNAME
  (when (fs/exists? (str config/docs-dir "/CNAME"))
    (println "Copying CNAME...")
    (fs/copy
      (str config/docs-dir "/CNAME")
      (str config/repo-dir "/public/CNAME")
      {:replace-existing true})
    (println "✓ CNAME copied"))

  (println "\n=== Assets copy complete ===\n"))

(comment
  ;; Test copying a single file
  (copy-post
    (fs/file config/posts-dir "techsposure" "2020-04-23-reitit-router-in-reframe.md"))

  ;; Run full copy
  (copy-all-content)

  ;; Copy assets
  (copy-assets))
