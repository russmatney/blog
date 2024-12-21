(ns tasks
  (:require
   [babashka.fs :as fs]))

;; vars

(def repo-dir (str (fs/home) "/russmatney/blog"))
(def posts-dir (str repo-dir "/docs/posts"))
(def org-garden-dir (str (fs/home) "/todo/garden"))
(def org-daily-dir (str (fs/home) "/todo/daily"))


;; posts

(defn ->post
  [{:keys [post-path garden-path]
    :as   post}]
  (let [path (or post-path garden-path)]
    (assoc
      post
      :fname (fs/file-name path)
      :last-modified (fs/last-modified-time path)
      )))

(defn local-post-paths []
  (->> (concat (fs/glob (str posts-dir) "*.md")
               (fs/glob (str posts-dir) "**/*.md"))
       (map str)))

(defn local-posts []
  (->>
    (local-post-paths)
    (map (fn [path] (->post {:post-path (str path)})))))

(comment
  (local-posts))

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
