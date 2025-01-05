(ns blog.garden
  (:require
   [clojure.set :as set]
   [org-crud.core :as org-crud]

   [blog.config :as config]
   [blog.post :as post]
   [babashka.fs :as fs]))

(defonce garden-notes (atom nil))

(defn all-garden-notes []
  (when-not @garden-notes
    (reset! garden-notes
            (org-crud/dir->nested-items config/org-garden-dir)))
  @garden-notes)

(defn notes-with-tags [tags]
  (->>
    (all-garden-notes)
    (filter (fn [note] (set/subset? tags (:org/tags note))))))

(comment
  (count
    (->>
      (notes-with-tags #{"post" "published"})
      #_(take 1)
      )))

(defn garden-note-posts []
  (->>
    (notes-with-tags #{"post" "published"})
    (map (fn [note]
           ;; TODO merge ':post/' keys into org note
           (merge note #_(post/note->post note))
           ))))
