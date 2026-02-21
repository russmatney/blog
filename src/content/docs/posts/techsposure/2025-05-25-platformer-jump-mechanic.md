---
title: "platformer-jump-mechanic"
date: 2025-05-25
category: techsposure
tags: [tech]
---

# Platformer Jump Mechanic

TODO rewrite this into something more consumable

> The below is a copy-pasta from my mind-garden. I'd like to rewrite it into
> a proper post covering jump-impl resources for 2d platformers - stay tuned!

> Ideally we'll work up to an example repo for this + a twitch and youtube video companion.

# garden note: Jump Mechanic

Seems simple, unfortunately it's not.

In my next jump implementation, I'm going to do that height-as-input style, and
be sure to include variability (small/large jumps). That control should make for
a better game-feel, and support more flexible level design. (Update: I did it!
see 'Implementation' below)

## implementation

beehive's platformer (sidescroller) machine now supports adjustable jump based
on rise/fall time and height

https://github.com/russmatney/dino/blob/96f826b322e9a20e17e58963988269ef660e0e1a/src/dino/players/SSPlayer.gd#L45-L53

The jump/fall code is part of a state machine:
- https://github.com/russmatney/dino/blob/96f826b322e9a20e17e58963988269ef660e0e1a/src/dino/players/sidescroller_machine/Jump.gd
- https://github.com/russmatney/dino/blob/96f826b322e9a20e17e58963988269ef660e0e1a/src/dino/players/sidescroller_machine/Fall.gd

There's other stuff in there, but it's fairly flexible if you're cozy in the beehive state machines.

## jump defined by height and/or time

- tutorial (gdscript): https://www.youtube.com/watch?v=IOe1aGY6hXA
- gdc talk (w/ annoying audio blips): https://www.youtube.com/watch?v=hG9SzQxaCm8
- gist (gdscript): https://gist.github.com/sjvnnings/5f02d2f2fc417f3804e967daa73cccfd
