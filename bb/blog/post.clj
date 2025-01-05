(ns blog.post
  (:require
   [clojure.string :as string]
   [babashka.fs :as fs]

   [blog.dates :as dates]
   [blog.config :as config]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; ->post

(defn ->post
  [{:keys [garden-path]
    :as   post}]
  (let [path                      (:post/path post garden-path)
        fname                     (fs/file-name path)
        {title :title date :date} (dates/parse-datey-fname fname)]
    ;; TODO last time git-touched (all times git-touched?)
    ;; TODO parse tags, front-matters?
    ;; TODO parse dates for non-datey-filenames
    (cond-> post
      true (assoc
             :post/title title
             :post/fname fname
             :post/parent-fname (fs/file-name (fs/parent path))
             :post/last-modified (fs/file-time->millis (fs/last-modified-time path))
             )

      (:post/path post) (assoc :post/relative-url
                               (string/replace path config/docs-dir ""))
      (not (nil? date)) (assoc :post/date-created date))
    ))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; post helpers

(defn sort-latest-created-first [posts]
  (->> posts
       (sort-by :post/title)
       (sort-by :post/date-created dates/sort-latest-first)
       ))

(defn sort-oldest-created-first [posts]
  (->> posts
       (sort-by :post/title)
       (sort-by :post/date-created dates/sort-chrono)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; post getters

(defn posts-in-dir [directory]
  (->> (fs/glob (str directory) "*.md")
       (map (fn [path] (->post {:post/path (str path)})))))

(defn local-posts []
  (->> (concat (fs/glob (str config/posts-dir) "*.md")
               (fs/glob (str config/posts-dir) "**/*.md"))
       (map (fn [path] (->post {:post/path (str path)})))
       ))

(defn hundos-posts []
  (posts-in-dir config/hundos-dir))

(comment
  (local-posts)
  (hundos-posts))

(defn org-root-files []
  (->>
    (fs/glob (str config/org-dir) "*.org")
    (map (fn [path] (->post {:garden-path path})))))

(defn garden-posts []
  (->>
    (fs/glob (str config/org-garden-dir) "*.org")
    (map (fn [path] (->post {:garden-path path})))))

(defn daily-files []
  (->>
    (fs/glob (str config/org-daily-dir) "*.org")
    (map (fn [path] (->post {:garden-path path})))))

(comment
  (org-root-files)
  (garden-posts)
  (daily-files))
