---
title: Emacs, Cider, Nrepl Debugging Keywords
date: 2020-04-27
tags:
  - tech
  - clojure
---

One of the most enjoyable and productive features of Clojure is
the interactive, repl-driven development. But when some link in the
tooling-chain breaks, that productivity comes to a screeching halt.
Most issues can be solved, but because of all the magic, it is difficult
to learn where to look for the proper error message.

This post is focused on developing Clojure via Emacs with
[Cider](https://github.com/clojure-emacs/cider) and
[nREPL](https://github.com/nrepl/nrepl). In the future, I'll cover
[Inf-Clojure](https://github.com/clojure-emacs/inf-clojure),
[Babashka](https://github.com/borkdude/babashka), and socket-repls, once I have
a chance to dive in and figure out what those things are. :P

I also want to caveat and ask for feedback here - I'm just comfortable enough
with clojure to develop and debug these tools as a consumer, but wouldn't claim
to understand exactly how all these pieces interact. I'm writing this to try to
establish a better understanding myself, so please reach out to help fill any
gaps left untouched.

# The Goal

Emacs lets you edit your code, but with Cider and nREPL, you can send the code
to an external process that evaluates and returns the results. This is the joy
of repl-driven development - evaluating code in the editor itself for a _very_
tight feedback loop.

# The (P)Layers

## Emacs

At its base, emacs lets you edit files and call emacs-lisp functions.

## nREPL

nREPL provides support for evaluating clojure code. It provides a client and
server and various related tools.

## Cider

Cider is an emacs-lisp library for interacting with an nREPL server from emacs.
It provides functionality for starting an nREPL server entirely within emacs,
or for connecting to an external, already-running nREPL server.

## Sesman

Sesman is a generic session manager for emacs-based IDEs. Cider uses it to
manage the nREPL server that it starts up when you `jack-in`.

# When things go wrong

There comes a time, hopefully not too often, when you're staring at your code,
wondering what's going wrong (or right) in the background. `cider-eval` isn't
returning any values (or errors), go-to-definition is broken, the usual
font-faces are missing. What's going on?

I've been in emacs for less than 5 years, so things like checking \*Messages\*
when thing go wrong is not always top of mind. Here are a handful of things
you can check to get more information about the state of things.

## Emacs \*Messages\* buffer

Messages will show the mini-buffer's history, cider-jack-in error
messages, and the command attempted by cider-jack-in.

## \*cider-repl\* and \*cider-error\* buffers

If things have already started up, cider should have a clj and/or cljs buffer.
It may also have a \*cider-error\* buffer, usually when something fails to
compile, or an evaluated command throws an exception.

## nREPL logging buffer

nREPL provides a logging buffer of its own, but it defaults to disabled. You
can enable it via `M-x nrepl-toggle-message-logging`. Once enabled, future
messages will show up in the `*nrepl messages ...*` buffer.

## Dropping dev-time java optimizations

One quirk of clojure/java development is that the JVM, by default, throws away
some stacktraces as an optimization, which can lead to a useless error like
`NullPointerException` with no stacktrace. This can be disabled by passing
jvm options during start-up.

```clojure
;; deps.edn
{:aliases
 {:dev {:jvm-opts ["-XX:-OmitStackTraceInFastThrow"]}}
```

Then, you can pass this option to your cider-jack-in command by setting
`cider-shadow-cljs-global-options` (or the non-shadow form) to `"-A:dev"`.

You could set it in your global emacs config, but since it's tied to the
project's `deps.edn`, it makes more sense in the project's `.dir-local.el`.

```emacs-lisp
;; .dir-locals.edn
((clojure-mode
  (cider-shadow-cljs-global-options . "-A:dev")))
```

In a future post, I'll cover how you can add more cider `.dir-locals`, so you
can finally cider-jack-in without all the prompts.

## Sesman commands

Sesman manages your cider connections.

- `sesman-browser` shows all current cider connections
- `sesman-restart` kills and restarts your cider-jack-in or cider-connect,
  passing the same parameters you selected the first time, which saves the
  trouble of revisiting the start-up prompts.

I've not explored sesman much - there could be more gems in there!

## General emacs

- `list-processes`
- `kill-processes`

These will help you figure out just what work emacs is handling for you, and
help you kill running processes.

## General linux

Via terminal:

- `killall emacs`

I'm not above a `killall emacs` when it's that kind of day... but hopefully
you don't need to resort to this.

# Other nuggets

## Cider

- `cider-switch-to-repl-buffer`
  - navigates to the connected repl buffer.
  This is useful when you are moving between clj and cljs files

## General Emacs

- `M-x describe-key` for more about any keybinding
- `M-x describe-mode` for more about the current mode and all keybindings it
  carries

# Debugging is about information gathering

When things go wrong, it's a chance to learn more about what and why. The first
few months of clojure for me were an oscillation between the magic and the
darkness. Progress has come after learning about these buffers, commands, and
getting a clearer understanding of how these tools interact.

What else do you use to find your bearings in clojure-land?
