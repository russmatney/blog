(ns blog.markdown
  (:require
   [clojure.string :as string]
   [tick.core :as t]

   [post :as post]
   [dates :as dates]
   ))


(def exclude-from-index #{"README.md" "_sidebar.md"})

(defn post->md-text-link
  [{:post/keys [relative-url title]}]
  (str "[" title "](" relative-url ")"))

(defn posts-index [{directory     :directory
                    posts         :posts
                    ->text        :->text
                    skip-group-by :skip-group-by
                    }]
  (let [posts (->> (or posts (post/posts-in-dir directory))
                   (remove (comp exclude-from-index :post/fname))
                   post/sort-latest-created-first)]
    (if skip-group-by
      (->> posts
           post/sort-latest-created-first
           (map ->text)
           (string/join "\n"))
      (->> posts
           (group-by (fn [post] (when (:post/date-created post)
                                  (t/format "MMMM YYYY" (:post/date-created post)))))
           (sort-by (fn [[_group psts]] (some-> psts first :post/date-created))
                    dates/sort-latest-first)
           (mapcat (fn [[group posts]]
                     [(str "\n\n" (or group "Date Unspecified") "\n\n")
                      (->> posts
                           post/sort-latest-created-first
                           (map ->text) (string/join "\n"))]))
           (string/join "\n")))))
