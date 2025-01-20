(ns blog.tasks
  (:require
   [babashka.fs :as fs]

   [blog.pages :as pages]
   [blog.garden :as garden]
   [blog.config :as config]
   ))

(defn copy-in-devlog-presentations []
  (let [devlog-html-paths (fs/list-dir config/org-garden-dir "devlog*.html")]
    (fs/create-dirs config/html-dir)
    (->> devlog-html-paths
         (map (fn [p] (fs/copy p (str config/html-dir "/" (fs/file-name p)))))
         doall)
    )
  )

(defn regen-all-pages []
  ;; need to gen garden notes before indexes!
  ;; (garden/regenerate-all-garden-posts)
  (pages/write-indexes-and-sidebars))

(comment
  (regen-all-pages))

