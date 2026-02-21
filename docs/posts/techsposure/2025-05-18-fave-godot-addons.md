---
title: ""fave-godot-addons""
date: 2025-05-18T00:00-04:00[America/New_York]
category: techsposure
tags: [tech]
---

# Fave Godot Addons

A (hopefully well-maintained) list of Godot Addons that I love to `add on`.
Amirite? `add on`?!

> Apologies for that lame pun.

Not too much yet - this post will grow! I intend to be able to point folks here
as a sort of personal 'awesome-godot-addons' list.

Right now these are sorted alphabetically.

Note there is a bit of bias - some of these are addons I've created.

- [Aseprite Wizard](#Aseprite-Wizard)
- [Bones](#Bones)
- [GDUnit](#GDUnit)
- [Input Helper](#Input-Helper)
- [Log.gd](#Loggd)
- [MetSys](#MetSys)
- [Pandora](#Pandora)
- [Phantom Camera](#Phantom-Camera)
- [Sound Manager](#Sound-Manager)

## Aseprite Wizard

- [github.com/viniciusgerevini/godot-aseprite-wizard](https://github.com/viniciusgerevini/godot-aseprite-wizard)
- [docs](https://thisisvini.com/aseprite-wizard/)

Out of the box time-savings with this one! You can drag+drop an aseprite file
onto an AnimatedSprite2D, and it'll create the sprite frames for you using your
your tags from Aseprite.

## Bones

- [github.com/russmatney/bones](https://github.com/russmatney/bones)

A suite of addons that I pulled out of
[Dino](https://github.com/russmatney/dino). Should perhaps be broken down
further at some point - but I'm letting these tools evolve a bit first.

## GDUnit

- [github.com/MikeSchulze/gdUnit4](https://github.com/MikeSchulze/gdUnit4)
- [GDUnit docs](https://mikeschulze.github.io/gdUnit4/)
- [https://github.com/MikeSchulze/gdUnit4-action](https://github.com/MikeSchulze/gdUnit4-action)

A super solid Godot unit test framework - includes a companion github action
workflow!

There is also [GUT](https://github.com/bitwes/gut), but I've found I prefer GDUnit's handling of [certain
crash-situations](https://github.com/bitwes/Gut/issues/362#issuecomment-1141272385).

## Input Helper

- [github.com/nathanhoad/godot_input_helper](https://github.com/nathanhoad/godot_input_helper)
- [docs](https://github.com/nathanhoad/godot_input_helper/tree/main/docs)

Mics Input Handling boilerplate for managing changing controllers, detecting
inputs, etc. Quite useful, and something you'd probably end up writing yourself
anyway!

## Log.gd

- [github.com/russmatney/log.gd](https://github.com/russmatney/log.gd)
- [docs](https://russmatney.github.io/log.gd)

A `print()` replacement that adds colors to Godot's output window. Includes a
prefix with the script and line number that made the call.

## MetSys

- [github.com/KoBeWi/Metroidvania-System](https://github.com/KoBeWi/Metroidvania-System)
- [docs](https://github.com/KoBeWi/Metroidvania-System/wiki)

A solid set of building blocks for creating Metroidvanias in Godot. Check it out!

## Pandora

- [github.com/bitbrain/pandora](https://github.com/bitbrain/pandora)
- [docs](https://bitbra.in/pandora/#/)

> I adopted Pandora before fully understanding Godot's Custom Resources - so
> lately I try to go as far as I can with those before bringing in the
> full-Pandora experience.

Pandora provides some excellent UX improvements to Custom Resources, with the
added bonus of managing _entities_ and derived _instances_ of those entities.
It's quite nice!

## Phantom Camera

- [github.com/ramokz/phantom-camera](https://github.com/ramokz/phantom-camera)
- [docs](https://phantom-camera.dev/)

All kinds of camera features that you'll want, available out of the box, and
quite customizable. Check it out!

## Sound Manager

- [github.com/nathanhoad/godot_sound_manager](https://github.com/nathanhoad/godot_sound_manager)
- [docs](https://github.com/nathanhoad/godot_sound_manager/tree/main/docs)

Sound managing boilerplate that you'd probably have to write anyway! Much thanks
to Nathan Hoad for another great addon.
