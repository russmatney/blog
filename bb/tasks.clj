(ns tasks
  (:require
   [clojure.string :as string]
   [babashka.fs :as fs]))

;; vars

(def repo-dir (str (fs/home) "/russmatney/blog"))
(def docs-dir (str repo-dir "/docs"))
(def posts-dir (str repo-dir "/docs/posts"))
(def hundos-dir (str repo-dir "/docs/posts/100-worders"))
(def org-garden-dir (str (fs/home) "/todo/garden"))
(def org-daily-dir (str (fs/home) "/todo/daily"))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; data getters

;; posts

(defn ->post
  [{:keys [post-path garden-path]
    :as   post}]
  (let [path (or post-path garden-path)]
    (assoc
      post
      :fname (fs/file-name path)
      :parent-fname (fs/file-name (fs/parent path))
      :last-modified (fs/file-time->millis (fs/last-modified-time path))
      :relative-url (string/replace post-path docs-dir "")
      ;; last time git-touched (all times git-touched?)
      ;; category

      )))

(defn local-post-paths []
  (->> (concat (fs/glob (str posts-dir) "*.md")
               (fs/glob (str posts-dir) "**/*.md"))
       (map str)))

(defn local-posts []
  (->>
    (local-post-paths)
    (map (fn [path] (->post {:post-path (str path)})))))

(defn hundos-posts []
  (->> (fs/glob (str hundos-dir) "*.md")
       (map (fn [path] (->post {:post-path (str path)})))))

(comment
  (local-posts)
  (hundos-posts)
  )

;; garden files

(defn garden-paths []
  (->> (fs/glob (str org-garden-dir) "*.org")
       (map str)))

(defn garden-posts []
  (->> (garden-paths)
       (map (fn [path] (->post {:garden-path path})))))

(defn daily-paths []
  (->> (fs/glob (str org-daily-dir) "*.org")
       (map str)))

(defn daily-posts []
  (->> (daily-paths)
       (map (fn [path] (->post {:garden-path path})))))

(comment
  (garden-posts)
  (daily-posts)
  )

;; posts side bar

(defn write-sidebar [directory]
  (let [posts (->> (fs/glob directory "*.md")
                   (map #(->post {:post-path (str %)}))
                   (remove (fn [{fname :fname}]
                             (#{"README.md" "_sidebar.md"} fname)))
                   (sort-by :last-modified <))
        post-links
        (->> posts
             (map (fn [{:keys [relative-url fname]}]
                    (str "* [" fname "](" relative-url ")"))))]
    (spit
      (str directory "/_sidebar.md")
      (string/join "\n" post-links))))

(comment
  (write-sidebar hundos-dir)
  )

(defn write-index [directory]
  (let [posts (->> (fs/glob directory "*.md")
                   (map #(->post {:post-path (str %)}))
                   (remove (fn [{fname :fname}]
                             (#{"README.md" "_sidebar.md"} fname)))
                   (sort-by :last-modified <))
        post-links
        (->> posts
             (map (fn [{:keys [relative-url fname]}]
                    (str "* [" fname "](" relative-url ")"))))]
    (spit
      (str directory "/README.md")
      (str
        "## " (fs/file-name directory)
        (str "\n\n")
        (str "Known colloquially as hundos")
        (str "\n\n")
        (string/join "\n" post-links)
        ))))

(comment
  (write-index hundos-dir)
  )
