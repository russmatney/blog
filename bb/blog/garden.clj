(ns blog.garden
  (:require
   [babashka.fs :as fs]
   [clojure.set :as set]
   [clojure.string :as string]
   [org-crud.core :as org-crud]
   [org-crud.markdown :as org-crud.markdown]

   [blog.config :as config]
   [blog.log :as log]
   [blog.post :as post]
   [blog.generate :as gen]))

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

(defn ->post [note]
  (merge note (post/->post {:garden-path (:org/source-file note)})))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; rendering

(defn post->md-lines
  [{:post/keys [title]
    :org/keys  [name tags]
    :as        note}]
  (log/log "post to md lines" name title tags)

  ;; TODO dig into the md-lines generated here (drop the frontmatter)
  ;; we might want to support/parse a frontmatter or otherwise associate metadata
  ;; (lots of frontmatters already exist on old posts)
  ;; TODO we need a custom md-link impl/answer for id:<uuid> roam links/content
  ;; would be great to do something fancy - popups or expandable sections

  (let [md-item (org-crud.markdown/item->md-item note)]
    (->> md-item :body (string/join "\n"))))

(defn write-garden-post
  [{:post/keys [fname]
    :as        post}]
  (log/log "generating garden post" fname)
  (gen/write-page
    {:directory config/garden-posts-dir
     :path      fname
     :content   (post->md-lines post)}))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; cli input

(defn generate-post [opts]
  (let [path (:path opts)]
    (cond (#{"org"} (fs/extension path))
          (-> path
              org-crud/path->nested-item
              ->post
              write-garden-post)

          :else
          (log/log "Unsupported file extension" (fs/extension path))
          )))

(comment
  (->>
    (fs/list-dir config/org-garden-dir)
    (take 1)
    (first)
    ((fn [x] (generate-post {:path x})))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; bulk regeneration

(defn garden-note-posts []
  (->>
    (notes-with-tags #{"post" "published"})
    (map ->post)))

(defn regenerate-all-garden-posts []
  (->> (garden-note-posts)
       (map write-garden-post)
       (doall)))

(comment
  (regenerate-all-garden-posts))
