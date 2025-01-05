(ns blog.tasks
  (:require
   [blog.pages :as pages]))

(defn regen-all-pages []
  (pages/write-indexes-and-sidebars))

(comment
  (regen-all-pages))
