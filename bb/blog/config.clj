(ns blog.config
  (:require
   [babashka.fs :as fs]))


(def repo-dir (str (fs/home) "/russmatney/blog"))
(def docs-dir (str repo-dir "/docs"))

(def posts-dir (str repo-dir "/docs/posts"))
(def hundos-dir (str repo-dir "/docs/posts/100-worders"))
(def techsposure-dir (str repo-dir "/docs/posts/techsposure"))
(def getitwrite-dir (str repo-dir "/docs/posts/getitwrite"))
(def groks-dir (str repo-dir "/docs/posts/groks"))

(def devlogs-dir (str repo-dir "/docs/devlogs"))

(def org-garden-dir (str (fs/home) "/todo/garden"))
(def org-daily-dir (str (fs/home) "/todo/daily"))
(def org-dir (str (fs/home) "/todo"))
