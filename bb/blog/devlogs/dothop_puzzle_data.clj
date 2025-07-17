(ns blog.devlogs.dothop-puzzle-data
  (:require
   [clojure.string :as string]
   [babashka.fs :as fs]
   [babashka.json :as json]

   [blog.config :as config]))

(def dothop-speedrun-post-path
  (str config/devlogs-dir "/2025-06-10-dothop-speedrun.md"))

(def puzzle-data-path
  (str config/dothop-repo-dir "/data/puzzle_data.json"))

(defn puzzle-data []
  (-> puzzle-data-path slurp json/read-str))

(comment
  (->> (puzzle-data) (take 1))

  ;; puzzle-data blobs look like:
  {:puzzle_lines        ["......." ".xooot." "......."]
   :turn_sum            "0"
   :puzzle_id           "1-1"
   :most_turns_count    0
   :least_choices_count 0
   :path_sum            "1 / 1"
   :width               7
   :most_choices_count  0
   :raw_puzzle_lines    [[nil nil nil nil nil nil nil] [nil "x" "o" "o" "o" "t" nil] [nil nil nil nil nil nil nil]]
   :winning_paths       1
   :dot_count           4
   :total_paths         1
   :world_name          "Them Dots"
   :least_turns_count   0
   :choice_sum          "0"
   :height              3}
  )

(defn md-link [txt uri]
  (str "[" txt "](" uri ")"))

(def fields
  {"World"             :world_name
   "Level"             (fn [data] (let [pid (:puzzle_id data)] (md-link pid (str "#_" pid))))
   "Dots"              :dot_count
   "Paths (wins/all)"  :path_sum
   "Choices (min/max)" :choice_sum
   "Turns (min/max)"   :turn_sum
   })

(defn build-stats-table [{:keys [puzzle-datas columns]}]
  (let [columns (or columns (keys fields))]
    (str
      "| " (string/join " | " columns) " |"
      "\n"
      "| " (string/join "|" (->> columns count range (map (fn [_] "----")))) " |"
      "\n"
      (string/join
        "\n" (->> puzzle-datas
                  (map (fn [data]
                         (str
                           "| "
                           (string/join " | " (->> columns (map (fn [k] ((fields k) data)))))
                           " |"))))))))

(comment
  (->>
    (puzzle-data)
    (take 4)
    ((fn [datas]
       (build-stats-table {:puzzle-datas datas})))

    (spit "./tmp-stats-table.md")))


(defn build-world-section
  [{:keys
    [puzzle-datas
     section-name]
    :as opts}]
  (str
    "\n"
    "# " section-name
    "\n\n"

    ;; full stat table
    (build-stats-table opts)

    "\n\n"

    (string/join
      "\n\n"
      (->> puzzle-datas
           (map
             (fn [data]
               (str
                 "## " (:puzzle_id data)
                 "\n\n"

                 ;; stats for just this level
                 (build-stats-table (-> opts (assoc :puzzle-datas [data])))

                 "\n\n"
                 (string/join "\n" (->> (:puzzle_lines data)
                                        (map #(str "    " %))))
                 "\n\n"

                 ;; gif tag
                 (str "<img src= \"_images/dothop/speedrun_clips/dothop_speedrun-"
                      (:puzzle_id data)
                      ".gif\" />")

                 "\n\n"

                 )))))))

(comment
  (->>
    (puzzle-data)
    #_(take 4)
    ((fn [datas]
       (build-world-section
         {:puzzle-datas datas
          :section-name "Winter"
          })))
    (spit "./tmp.md")))

(def post-intro "

# Dot Hop 1.0 Speedrun and Puzzle Data

- [Twitch VODs](https://www.twitch.tv/videos/2482512802?collection=0tCOXvfGRxhZiA)

# Intro

Some metadata and clips from my Dot Hop 1.0 speedrun.

## All puzzle stats

")

(defn generate-base-post-content []
  (let [puzzle-datas        (puzzle-data)
        datas-by-world-name (->> puzzle-datas (group-by :world_name))]
    (str post-intro
         "\n\n"
         (build-stats-table {:puzzle-datas puzzle-datas})
         "\n\n"
         (string/join
           "\n\n"
           (->> datas-by-world-name
                (map (fn [[world-name puzzle-datas]]
                       (build-world-section
                         {:puzzle-datas puzzle-datas
                          :section-name (str world-name
                                             " (World " (-> puzzle-datas first :world_i inc) ")")}))))))))

(comment
  (->>
    (generate-base-post-content)
    (spit #_"./tmp.md" dothop-speedrun-post-path)))
