---
title: "Adding dependencies during REPL-driven development"
date: 2020-04-28
category: techsposure
tags: [tech]
---



Developing in a repl is a different paradigm, and one that you won't want to
leave. It's like the fun part of web development, after you've experienced
proper hot-reloading and can't imagine living without it. Once you're in that
flow, you start to notice the things that throw a wrench in it. Please don't
break my flow!

One flow-breaking culprit you'll encounter is adding a new package. I recently
decided that this problem must be solved already, and sure enough, a search
revealed a few solutions. As always with Clojure, the community is great and the
tooling is evolving, so every piece of the chain has a history. My goal in
writing this is to share what worked for me and prompt anyone doing it better to
let me know what that is, because I want it!

## Resources

A thread in Clojureverse from January of this year covers this area somewhat:
[What’s the right way to hot-reload dependencies without restarting the repl?](https://clojureverse.org/t/whats-the-right-way-to-hot-reload-dependencies-without-restarting-the-repl/5357/10)

I know [rschmukler](https://github.com/rschmukler/) uses
[`wing.repl/sync-libs`](https://github.com/teknql/wing/blob/master/src/wing/repl.clj#L9)
for this, but I have not yet integrated that into my workflow, mostly because
the below worked for me without adding any new deps.

## One method

I could stand to take more advantage of the src/user.clj namespace, as
it is central to solving the need here. The user namespace seems to provide a
space for dev-only functions, and it is the default namespace entered when
starting most repls.

```
(ns user
  (:require
   [cemerick.pomegranate :refer [add-dependencies]]))

(defn add-dep
  [coords]
  (add-dependencies
    :coordinates coords
    :repositories (merge cemerick.pomegranate.aether/maven-central
                         {"clojars" "https://clojars.org/repo"})))

(comment
  (add-dep '[[teknql/systemic "0.2.0-SNAPSHOT"]])
  (add-dep '[[hawk "0.2.11"]])
  (add-dep '[[cuerdas "0.3.2"]])
  (add-dep '[[com.cognitect/transit-cljs "0.8.239"]
             [com.cognitect/transit-clj "0.8.300"]]))
```

Evaluating the above `(add-dep ...)` calls was enough for me to keep going
without a restart, and try out a library a bit before adding it to my
`deps.edn`. It does duplicate some of the work though - you'll still need to add
it elsewhere, or you'll have an issue the next time you start your repl.

This also implies a pomegranate dependency, but for emacs/cider users, these are
typically included already by cider's jack-in command, so there shouldn't be any
extra work beyond the above function.

## How do you handle this?

How do you dynamically add clojure deps? What else interrupts development flow
that shouldn't?

I'd love to build up a documented answer to every repl-flow interruption.
Fight for flow!
