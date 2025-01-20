(ns blog.config
  (:require
   [babashka.fs :as fs]))


(def repo-dir (str (fs/home) "/russmatney/blog"))
(def docs-dir (str repo-dir "/docs"))

(def posts-dir (str docs-dir "/posts"))
(def hundos-dir (str posts-dir "/100-worders"))
(def techsposure-dir (str posts-dir "/techsposure"))
(def getitwrite-dir (str posts-dir "/getitwrite"))
(def groks-dir (str posts-dir "/groks"))

;; used to both read and write to these files
(def garden-posts-dir (str posts-dir "/notes"))

(def devlogs-dir (str docs-dir "/devlogs"))
(def html-dir (str docs-dir "/html"))

(def org-dir (str (fs/home) "/todo"))
(def org-garden-dir (str org-dir "/garden"))
(def org-daily-dir (str org-dir "/daily"))
