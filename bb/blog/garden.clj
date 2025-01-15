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
   [blog.generate :as gen]
   [blog.dates :as dates]))

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
  (log/log "post->md-lines" name title tags)

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

(defn process-garden-note [opts]
  (-> (:path opts)
      org-crud/path->nested-item
      ->post
      write-garden-post))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; daily

(defn title->fname [title]
  (-> title
      (string/replace #"(,|\?|\.)", "")
      (string/replace #" " "-")))

(defn daily-path->date-str [path]
  (string/replace (fs/file-name path) #"\.org" ""))

(defn create-hundo [opts item]
  (let [date-fname  (daily-path->date-str (:path opts))
        title       (:org/name item)
        file-title  (title->fname title)
        hundo-fname (str date-fname "-" file-title ".md")
        hundo-path  (str config/hundos-dir "/" hundo-fname)]
    (log/log "creating hundo from daily" hundo-path)
    (gen/write-page
      {:directory config/hundos-dir
       :path      hundo-fname
       :content   (post->md-lines item)})))

(defn create-til [opts item]
  (let [path (:path opts)]
    (println "todo create tils" item)))

(defn process-daily-item [opts item]
  (cond
    (contains? (:org/tags item) "hundo")
    (create-hundo opts item)

    (contains? (:org/tags item) "til")
    (create-til opts item)))

(defn process-daily [opts]
  (->>
    (:path opts)
    org-crud/path->flattened-items
    (filter (comp seq :org/tags)) ;; must have a tag
    (map (partial process-daily-item opts))
    doall))

(comment
  (process-daily {:path "~/todo/daily/2025-01-14.org"}))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; cli input

(defn generate-post [opts]
  (let [path (:path opts)]
    (cond
      (#{"org"} (fs/extension path))

      (cond
        (#{"daily"} (fs/file-name (fs/parent path)))
        (process-daily opts)

        :else
        (process-garden-note opts)
        )

      :else
      (log/log "Unsupported file extension" (fs/extension path))
      )))

(comment

  (fs/file-name (fs/parent "~/todo/daily/2025-01-14.org"))
  (generate-post {:path "~/todo/daily/2025-01-14.org"})
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
