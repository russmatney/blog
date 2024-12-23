# Open Source

# Godot Addons

## Log.gd

A much-needed pretty printer for Godot.

You can drop-in `Log.pr` for `print` to get colorized, type-aware logs.

[Log.gd Repo](https://github.com/russmatney/log.gd)
| [Log.gd Docs](https://russmathey.github.io/log.gd)

* TODO: add godot addon library link
* TODO: add screenshots and logo

## Bones

A slew of experimental addons from Dino that have been grouped together and
slimmed down a bit.

I'm now consuming this in several of my Godot games, and
still ironing things out somewhat.

Once this feels more consumable, I'll submit it to the Godot Addon Library.

[Bones Repo](https://github.com/russmatney/bones)
| [Bones Docs](https://russmathey.github.io/bones)

## Dinky

This is intended to be a Godot + ink library for use with visual novels or
dialog in general.

Still very early days, but this builds an ink-based itch.io game successfully!

[Dinky Repo](https://github.com/russmatney/bones)
| [Thief Guard on itch](https://russmatney.itch.io/thief-guard)

# Other Tools

## Clawe

Clawe is a clojure monorepo housing a bunch of dev tools. It slowly consumed
several similar-enough projects, primarily to simplify repl-driven development
regardless of which one I want to work on.

The main `clawe` features are supporting a repo-based workflow - this lets me
support keybindings for toggling apps (terminal, emacs, godot) in a
project-specific context.

The `ralphie` namespace provides stateless, dependency-minimal interfaces with
external programs (like emacs, tmux, rofi, spotify, browsers, i3, sway, yabai, etc.). The `clawe`
namespace builds on top of that to offer window-manager conveniences that can be invoked via
the commandline (with [babashka](https://github.com/borkdude/babashka)).

The `doctor` namespace provides a full-stack clojure setup - the idea is a
health-check "dashboard" full of widgets for things like todos, wallpapers,
workspaces, repo git-statuses... whatever might be convenient. This used to
include blog build and deploy as well, but at the time of writing I'm phasing
that out.

This project has been through several phases and will likely go through several
more! Feel free to peruse the code or poke me about it, and hopefully I won't
ramble at you too much. I owe some learnings/tradeoffs content about many
different clawe things - more to come on that!

[Clawe Repo](https://github.com/russmatney/clawe)

## org-crud

A clojure api for reading/writing to/from emacs org-mode files.

[org-crud Repo](https://github.com/russmatney/org-crud)

## Clove

Clove is a (very) thin wrapper over Tauri, such that you can pass a url and
title to get a simple web viewer. I use this for Clawe/Doctor's topbar and
dashboard widgets.

[Clove Repo](https://github.com/russmatney/clove)

- TODO: link to clove/tauri blog posts

# Advent of Code

The annual Advent of Code challenges are super fun and usually inspiring and
empowering. Dot Hop and Dino both parse plaintext files into godot
datastructures, and I'm sure A.O.C. is to blame! It reminds me every year that
you can write your own DSLs... even if that's usually a terrible idea :P

* [Advent of Code - Clojure](https://github.com/russmatney/advent-of-code)
* [Advent of Code - Godot](https://github.com/russmatney/advent-of-godot)
* [Advent of Code - Gerbil](https://github.com/russmatney/advent-of-gerbil)
* [Advent of Code - Zig](https://github.com/russmatney/advent-of-zig)
