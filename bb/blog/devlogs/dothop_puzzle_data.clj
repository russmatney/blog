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
                      ".GIF\" />")

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

;; TODO seek for \n--- snip ---\n and don't wipe the posts' intro
(def post-intro "

--- snip ---

Everything below here was auto-generated
by [`bb/blog/devlogs/dothop_puzzle_data.clj`](https://github.com/russmatney/blog/blob/ba2db0263b953871d39444c62881079a52e10bef/bb/blog/devlogs/dothop_puzzle_data.clj#L131)

# Dot Hop 1.0 Speedrun and Puzzle Data

I streamed a full playthrough of Dot Hop 1.0 in June! (link above)

I've been thinking about Dot Hop puzzle metrics - here I'll present some
alongside the solutions. The result is this mess of a Dev Log.

Presenting: A bunch of Dot Hop Puzzle Spoilers
Alongside: the raw plain-text puzzle input
Tables of path, choice, and turn counts.

> Path count: How many possible ways can this puzzle be brute-forced?
> Winning path count: How many are 'wins'?
> Choices: How many times did the player have multiple-next-dot options (besides 'undo')
> Turns: How many times did the player change direction (besides 'undo')

I'm hopeful to use metrics like these to work with puzzle difficulty,
especially for evaluating generated puzzles. It's also been inspiring new ideas:
e.g. why don't we skip to the next choice? why require more input?

Anyway, here comes a bunch of data and gifs for each puzzle - good luck in there!

?> **Navigation tip**: The `1-1` level links in the tables are intended to help navigate this thing.

!> **Warning**: These tables render pretty poorly on mobile right now.\
!> I'm hoping to improve that soon....

# All puzzle stats

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
