(ns blog.tasks
  (:require
   [blog.pages :as pages]
   [blog.garden :as garden]
   ))

(defn regen-all-pages []
  ;; need to gen garden notes before indexes!
  ;; (garden/regenerate-all-garden-posts)
  (pages/write-indexes-and-sidebars))

(comment
  (regen-all-pages))
