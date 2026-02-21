---
title: """Reitit Router in Re-frame"""
date: 2020-04-23
category: techsposure
tags: [tech]
---




I've built [re-frame](https://github.com/day8/re-frame) apps for about a year
now, and have always used [secretary](https://github.com/clj-commons/secretary)
and [accountant](https://github.com/venantius/accountant) for routing. In a
recent project, I looked into [Reitit](https://github.com/metosin/reitit), a
router from metosin to see how it compared. In my opinion, Reitit is well
featured, supported my needs very well, and has been easier to work with.

I recently added Reitit to my
[Starters](https://github.com/russmatney/starters) repo, which I introduced a
[few posts ago](http://russmatney.com/2020-02-21-clojure-repo-starters/). To
summarize, Starters is intended to live as a documented example for getting
full-stack clojure apps setup and running with some useful features.

I used a few sources as references to get this working the first time:

- The [full example from metosin](https://github.com/metosin/reitit/blob/master/examples/frontend-re-frame/src/cljs/frontend_re_frame/core.cljs).
- A [Mui Templates project](https://github.com/dakra/mui-templates) by
  [dakra](https://github.com/dakra)
  - A larger working example
  - Includes passing the router through to a nav bar (fancy, and not featured in
    this post)

## Rough outline for adding the Reitit router

Not too in depth, but hopefully touches on the required pieces.

### deps.edn (or shadow-cljs.edn)

These were the latest at the time of writing:

```clojure
metosin/reitit       {:mvn/version "0.4.2"}
metosin/reitit-malli {:mvn/version "0.4.2"}
```

### routes.cljs

I have not included all the code, so be sure to read the linked examples above
and/or
[routes.cljs](https://github.com/russmatney/starters/blob/430a987bcad395b6970e8391c7d0be9c8678c36f/fullstack/src/fullstack/ui/routes.cljs)
in the starters repo.

#### Subs, Events, Effects

```clojure
(rf/reg-sub
  :current-route
  (fn [db]
    (:current-route db)))

(rf/reg-event-fx
  :navigate
  (fn [_cofx [_ & route]]
    {:navigate! route}))
```

I've included only the above sub and event as they are the most relevant to a
consumer. These are intended to support usage throughout the app. Note that they
are not tied to the namespace - you can do that if you'd like, but it's not
strictly necessary.

#### Routes as data

The heart of the routes concept: the description of the routes as data.

These are simple definitions providing a name, view, and controllers to each
route.

```clojure
(def routes
  ["/"
   [""
    {:name      :routes/home
     :view      home/page
     :link-text "Home"
     :controllers
     [{:start (fn []
                (println "Entering home page"))
       :stop  (fn []
                (println "Leaving home page"))}]}]
   ["files"
    {:name      :routes/files
     :view      files/files-page
     :link-text "Files"
     :controllers
     [{:start (fn []
                (println "Entering files page")
                (rf/dispatch [::events/fetch-files]))
       :stop  (fn []
                (println "Leaving files page"))}]}]
   ["files/:id"
    {:name      :routes/file
     :view      files/file-page
     :link-text "Files"
     :coercion  reitit.coercion.malli/coercion
     :params    {:path [:map [:id string?]]}
     :controllers
     [{:parameters {:path [:id]}
       :start      (fn [{:keys [path]}]
                     (let [file-id (:id path)]
                       (println "Entering files/:id page for id" file-id)
                       (rf/dispatch [::events/fetch-files])
                       (rf/dispatch [::events/set-active-file-id file-id])))
       :stop       (fn []
                     (println "Leaving files page"))}]}]])
```

I've included the third definition to give exposure to coercion, which metosin
offers a few solutions for. I've chosen
[malli](https://github.com/metosin/malli)-style here. For more on what coercion
is, see [reitit's
docs](https://github.com/metosin/reitit/blob/master/doc/coercion/coercion.md).

#### init-routes!

```clojure
(defn init-routes! []
  (js/console.log "initializing routes")
  (rfe/start!
    router
    on-navigate
    {:use-fragment true}))
```

`init-routes!` should be called at app startup, usually somewhere in your
app.core namespace.

### app/core.cljs

```
(defn mount-root []
  (routes/init-routes!)
  (reagent/render [views/root]
                  (.getElementById js/document "app")))
```

Make sure to call `routes/init-routes!` before rendering your root view via
reagent.

### app/views.cljs

A simple root component, that dispatches to the current-route's `:view`.

```clojure
(defn main-page
  []
  (let [current-route @(rf/subscribe [:current-route])]
    [:div
     (when current-route
       [(-> current-route :data :view)])]))

(defn root []
  [:div#root
   {:style {:width "100vw"}}
   [main-page]])
```

## Now use it!

At this point, your app should support Reitit. How to use it?

```clojure
(comment
  ;; in your repl
  (rf/dispatch [:navigate :routes/files]))

;; example button
[:input
 {:on-click #(rf/dispatch [:navigate :routes/files])
  :type     "button"
  :value    "Navigate to Files Page"}]

(let [file {:id "some-id" :name "some-filename"}]
  [:input
   {:on-click #(rf/dispatch [:navigate :routes/file file])
    :type     "button"
    :value    (str "Navigate to " (:name file))}])
```


# From here

Again, the [starters repo]() contains a fully working example if you'd like to
see all the code.

I can recommend this router from experience with my latest re-frame app, but if
you're feeling adventurous, you might also look into
[kee-frame](https://github.com/ingesolvoll/kee-frame), which comes with routing
and controllers bundled in, among other things. I haven't touched it yet, but
hope to take it for a spin soon.
