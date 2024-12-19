(ns tasks
  (:require
   [babashka.fs :as fs]))

(defn post-paths []
  (->>
    (fs/glob (str (fs/home) "/todo/garden") "*.org")
    (sort-by (comp str fs/last-modified-time))
    reverse ;; TODO lol do this better
    (take 50)
    set))

(comment
  (post-paths))
