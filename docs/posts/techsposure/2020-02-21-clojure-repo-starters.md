---
title: ""Clojure Repo Starters""
date: 2020-02-21
category: techsposure
tags: [tech]
---



I haven't followed through on this yet, but writing this will add the right
amount of anonymous social pressure, so here goes.

## Motivation

Clojure is my favorite language to work with these days, but it's not as
approachable as other language communities. Approachability has gotten even
better in the last few years, which is awesome, but there is still a discovery
problem. If you aren't looking in the right places or talking to someone keeping
up, you'll find content that was helpful four years ago, but is now outdated.

A friend showed interest in diving into Clojure recently, so a question comes to
mind - what's the best way to help someone on-board? One thought is a few
real repo templates. When you jump into a new language, it's helpful to
clone something and start poking at it.

In Clojure, there's a great ecosystem around [leiningen](https://leiningen.org/)
and it's template system. But what if leiningen itself is no longer necessary?
It's a great tool, but just isn't one that I use in my projects anymore, not
since clojure moved to stronger [deps and cli
support](https://clojure.org/guides/deps_and_cli) support. Another example is
the dramatic improvement [shadow-cljs]() has made on integrating clojurescript
frontends with npm modules. I spent a week learning about cljsjs, only to find
that whole branch of solutions is not necessary.

That's the primary motivation for the
[starters](https://github.com/russmatney/starters) repo.

## Do it yourself!

It's important to do things yourself, from scratch, to learn all the ins and
outs. Going through all the bumps in the road, will build up empathy for the
current tool chain. In this context, I'd encourage you to make your own
`starters` repo - write your own templates, learn what you're putting in there.

With that in mind, what I'm shooting for here is documenting the structures I'm
using. This will support sharing as well as getting feedback. Please feel free
to open issues, PRs, fork it and run, or just do it yourself from scratch and
drop a link. I'd love to know what you think the latest and greatest is.

## Goals

- Create a re-usable set of `starters` that can be referenced/copy-pasted for
  starting miscellaneous projects.
- Provide exposure for other developers to clojure project structures that are
  both real and minimal.
- Provide a readme with enough context and magic keywords that a developer with
  experience installing cli tools and configuring an editor can read get started
  from.
- Hopefully, keep it reasonably up-to-date, using the latest recommended
  tooling.

## Non-goals

- A fancy templating engine with feature-flags and project naming
- Automation of installing these templates

It's not _that_ much work to clone, move some dirs around, and re-init git.
There could be a script (there probably will be at some point), but it'd be
opinionated, and it's not the target use-case.

What I think is more likely: you've started a project already and are debugging,
wondering just how things are supposed to be setup anyway, so you need an
example. Besides, Clojure tooling is simple enough that we should just be doing
this from scratch anyway.

## Opinionated?

It will of course be an opinionated set of templates... apologies in advance! I
expect it to reflect the path into Clojure I've taken, which only started a year
ago. You'll see just what opinions below.

# The Starters

## ClojureScript Frontend

- [Shadow-cljs](https://github.com/thheller/shadow-cljs) as a live-reload dev
  and build tool
- Deps handled via tools.deps
- [Re-frame](https://github.com/day8/re-frame) as a framework
- [Reagent](https://github.com/reagent-project/reagent) for React components
- [Garden](https://github.com/noprompt/garden) and
  [Herb](https://github.com/roosta/herb) for css handling
- [Kaocha](https://github.com/lambdaisland/kaocha) for watching and running unit
  tests
- An example npm integration. At the time of writing, no necessary repo comes to
  mind... an example might be something like React Material UI, or a
  non-trivial date picker.

## Clojure Full-stack application

- The above clojurescript application.
- [Pneumatic-tubes](https://github.com/drapanjanas/pneumatic-tubes) for sockets
  and an event-driven system that integrates well with re-frame.
- [Systemic](https://github.com/teknql/systemic) for running things REPL first.

I expect the above to get rounded out at some point with an http server... maybe
[Pedestal](https://github.com/pedestal/pedestal)?

## Clojure CLI Application

I went around this horn a few times last year, but have landed on CLI-matic and
GraalVM as a nice way of writing clojure instead of bash.

- [CLI-matic](https://github.com/l3nz/cli-matic) for simple, declarative CLI
  commands and args.
- [GraalVM](https://github.com/oracle/graal) for compiling it for speed.

# Lingering thoughts

Perhaps `examples` is a better repo name?

Some other things to maintain along with the feature sets is the expected usage.
What is the expected dev experience here? What cli commands need to be run to
build things, and how is the experience supported from emacs (more bias poking
out)? These are difficult things to learn without direct pairing.

Hopefully getting these starters together and documenting them will bring more
clarity to the every day in clojure, and for more than just myself.
