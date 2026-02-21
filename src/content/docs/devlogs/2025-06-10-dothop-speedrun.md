---
title: "Devlog: Dot Hop Speed Run and Puzzle Spoilers"
date: 2025-06-10
category: devlogs
tags: [devlog, dothop]
---

# Devlog: Dot Hop Speed Run and Puzzle Spoilers

!> **Spoiler Warning**: Animated Dot Hop Puzzle Solutions below!

2025 July 18 17:00

that's after 5 on Fridaze folks!\
you're out in my seat at the bar\
i'm `<leader-u-c>` to disable autocomplete in neovide

> popping up like fireworks\
> someone's trying to type here\
> driving me bananas\
> \<switches to emacs\>\
> oh thank god it's gone

### I'm working on Dot Hop 2.0!

I'm hoping to dive on a Dot Hop refactor tonight - i started a huge one called
`GAMEDEF IS DEAD` today. Here's links to some related commits confusingly buried
in this list of links helpful for the rest of this post:

- [Dot Hop on Steam](https://store.steampowered.com/app/2779710/Dot_Hop/)
- [Dot Hop repo on Github](https://github.com/russmatney/dothop)
- [`GAMEDEF IS DEAD` Commits](https://github.com/russmatney/dothop/commits/main/?since=2025-07-18&until=2025-07-18)
- [Twitch Stream that all the GIFs below are cut from](https://www.twitch.tv/videos/2482474110)
  - (if the stream has expired from the web there are should be a collection of
    VOD/highlights around somewhere)

> Dot Hop for iOS and Android is in beta!\
> Ping me on Discord for an invite!

### sneak peak: GameDef is Dead

I'm completely removing all the `GameDef` and `game_def` in the repo.\
It was responsible for wrapping the `ParsedGame`.\
(which is a raw array of strings and various metadata)\
`GameDef` also promoted a bunch of helper puzzle state datas.

> mostly it improved things from the parse that PuzzleScript (separate library) supported
> e.g. a 'legend' for mapping the raw string inputs to lists of OR + AND
> > (now mostly referred to as cell data)
> > (by now i've just hardcoded the legend - it's flexiblity i don't need rn)

This morning i created a `EditorInputPlugin` that parses `.puzz` plaintext files
into a Godot `Custom Resource`.

!> if that one line doesn't cheer you up, you might hate the rest of this post :sweat_smile:

We now support `dothop-tutorial.puzz` as a full-on `PuzzleSetData` godot 'resource'.
It's read-only but will be the core of the new `PuzzleStore` (coming soon to a
`src/*` near you!).

....which is probably a stateless Autoload for all puzzle data? (Maybe exposed
as lists/filters of `PuzzleDef`s?)

I recently finished two other refactors:

- `DotHopPuzzle` <> `PuzzleState` extraction
- `PuzzleAnalysis` <> `StatLogger` puzzle-data exporter

> `StatLogger` generated the json that supports the rest of this post.\
> Thanks, `StatLogger`.\
> You're an inspiration to us all.

---


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

!> **Warning**: These tables render pretty poorly on mobile right now. I'm hoping to improve that soon....

# All puzzle stats

| World                | Level          | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------------------- | -------------- | ---- | ---------------- | ----------------- | --------------- |
| Them Dots            | [1-1](#_1-1)   | 4    | 1 / 1            | 0                 | 0               |
| Them Dots            | [1-2](#_1-2)   | 5    | 1 / 1            | 0                 | 4               |
| Them Dots            | [1-3](#_1-3)   | 6    | 1 / 4            | 1                 | 4               |
| Them Dots            | [1-4](#_1-4)   | 7    | 1 / 3            | 0                 | 6               |
| Them Dots            | [1-5](#_1-5)   | 5    | 1 / 3            | 1                 | 4               |
| Them Dots            | [1-6](#_1-6)   | 9    | 2 / 22           | 3                 | 6               |
| Them Dots            | [1-7](#_1-7)   | 8    | 2 / 12           | 3 / 4             | 3 / 7           |
| Them Dots            | [1-8](#_1-8)   | 8    | 1 / 7            | 3                 | 7               |
| Them Dots            | [1-9](#_1-9)   | 12   | 1 / 30           | 2                 | 9               |
| Them Dots            | [1-10](#_1-10) | 11   | 1 / 19           | 3                 | 9               |
| Them Dots            | [1-11](#_1-11) | 14   | 1 / 105          | 5                 | 11              |
| Them Dots            | [1-12](#_1-12) | 14   | 8 / 105          | 5 / 7             | 9 / 12          |
| A Spring in your Hop | [2-1](#_2-1)   | 5    | 1 / 4            | 1                 | 3               |
| A Spring in your Hop | [2-2](#_2-2)   | 8    | 1 / 4            | 1                 | 5               |
| A Spring in your Hop | [2-3](#_2-3)   | 11   | 1 / 11           | 1                 | 8               |
| A Spring in your Hop | [2-4](#_2-4)   | 12   | 1 / 12           | 2                 | 9               |
| A Spring in your Hop | [2-5](#_2-5)   | 11   | 1 / 17           | 3                 | 10              |
| A Spring in your Hop | [2-6](#_2-6)   | 12   | 1 / 41           | 4                 | 7               |
| A Spring in your Hop | [2-7](#_2-7)   | 13   | 2 / 45           | 3                 | 9               |
| A Spring in your Hop | [2-8](#_2-8)   | 15   | 2 / 125          | 4                 | 7 / 10          |
| A Spring in your Hop | [2-9](#_2-9)   | 18   | 1 / 147          | 5                 | 10              |
| A Spring in your Hop | [2-10](#_2-10) | 19   | 7 / 286          | 5 / 8             | 17              |
| A Spring in your Hop | [2-11](#_2-11) | 20   | 5 / 671          | 6 / 7             | 13 / 16         |
| A Spring in your Hop | [2-12](#_2-12) | 23   | 4 / 1738         | 7 / 9             | 11 / 14         |
| That's Just Beachy   | [3-1](#_3-1)   | 7    | 2 / 3            | 1                 | 6               |
| That's Just Beachy   | [3-2](#_3-2)   | 8    | 3 / 11           | 2 / 3             | 5 / 6           |
| That's Just Beachy   | [3-3](#_3-3)   | 10   | 2 / 6            | 2 / 3             | 8               |
| That's Just Beachy   | [3-4](#_3-4)   | 13   | 3 / 20           | 2 / 3             | 11              |
| That's Just Beachy   | [3-5](#_3-5)   | 10   | 2 / 7            | 2                 | 8               |
| That's Just Beachy   | [3-6](#_3-6)   | 14   | 2 / 12           | 3                 | 12              |
| That's Just Beachy   | [3-7](#_3-7)   | 16   | 1 / 24           | 3                 | 12              |
| That's Just Beachy   | [3-8](#_3-8)   | 14   | 2 / 44           | 3 / 4             | 11              |
| That's Just Beachy   | [3-9](#_3-9)   | 14   | 1 / 8            | 1                 | 11              |
| That's Just Beachy   | [3-10](#_3-10) | 23   | 17 / 399         | 5 / 10            | 11 / 19         |
| That's Just Beachy   | [3-11](#_3-11) | 20   | 6 / 582          | 5 / 8             | 13 / 15         |
| That's Just Beachy   | [3-12](#_3-12) | 19   | 4 / 610          | 9 / 10            | 17              |
| Leaf Me Alone        | [4-1](#_4-1)   | 4    | 1 / 3            | 1                 | 3               |
| Leaf Me Alone        | [4-2](#_4-2)   | 10   | 1 / 14           | 1                 | 6               |
| Leaf Me Alone        | [4-3](#_4-3)   | 11   | 3 / 24           | 2 / 3             | 8               |
| Leaf Me Alone        | [4-4](#_4-4)   | 12   | 1 / 7            | 2                 | 10              |
| Leaf Me Alone        | [4-5](#_4-5)   | 15   | 5 / 118          | 5 / 7             | 10 / 13         |
| Leaf Me Alone        | [4-6](#_4-6)   | 17   | 4 / 165          | 4 / 5             | 12 / 13         |
| Leaf Me Alone        | [4-7](#_4-7)   | 21   | 173 / 4985       | 8 / 13            | 8 / 19          |
| Leaf Me Alone        | [4-8](#_4-8)   | 18   | 13 / 265         | 5 / 9             | 11 / 15         |
| Leaf Me Alone        | [4-9](#_4-9)   | 22   | 7 / 1579         | 7 / 9             | 13 / 16         |
| Leaf Me Alone        | [4-10](#_4-10) | 15   | 1 / 14           | 2                 | 10              |
| Leaf Me Alone        | [4-11](#_4-11) | 20   | 41 / 1512        | 7 / 10            | 12 / 18         |
| Leaf Me Alone        | [4-12](#_4-12) | 21   | 12 / 1584        | 7 / 9             | 12 / 15         |
| Snow Way             | [5-1](#_5-1)   | 4    | 1 / 1            | 0                 | 0               |
| Snow Way             | [5-2](#_5-2)   | 7    | 1 / 3            | 1                 | 5               |
| Snow Way             | [5-3](#_5-3)   | 10   | 1 / 1            | 0                 | 6               |
| Snow Way             | [5-4](#_5-4)   | 8    | 2 / 10           | 2 / 3             | 7               |
| Snow Way             | [5-5](#_5-5)   | 16   | 7 / 123          | 4 / 7             | 15              |
| Snow Way             | [5-6](#_5-6)   | 16   | 2 / 113          | 5 / 7             | 12              |
| Snow Way             | [5-7](#_5-7)   | 17   | 1 / 28           | 2                 | 12              |
| Snow Way             | [5-8](#_5-8)   | 18   | 5 / 215          | 5 / 7             | 15 / 16         |
| Snow Way             | [5-9](#_5-9)   | 20   | 10 / 1422        | 7 / 11            | 13 / 17         |
| Snow Way             | [5-10](#_5-10) | 20   | 2 / 156          | 5 / 6             | 15              |
| Snow Way             | [5-11](#_5-11) | 18   | 5 / 316          | 6 / 8             | 13 / 14         |
| Snow Way             | [5-12](#_5-12) | 21   | 4 / 120          | 5 / 9             | 17 / 20         |
| Get Outer Here       | [6-1](#_6-1)   | 7    | 1 / 3            | 1                 | 6               |
| Get Outer Here       | [6-2](#_6-2)   | 9    | 1 / 18           | 3                 | 6               |
| Get Outer Here       | [6-3](#_6-3)   | 14   | 3 / 20           | 2 / 3             | 9               |
| Get Outer Here       | [6-4](#_6-4)   | 15   | 1 / 87           | 5                 | 11              |
| Get Outer Here       | [6-5](#_6-5)   | 20   | 5 / 142          | 5 / 6             | 14 / 17         |
| Get Outer Here       | [6-6](#_6-6)   | 20   | 2 / 166          | 4 / 5             | 16              |
| Get Outer Here       | [6-7](#_6-7)   | 18   | 2 / 68           | 4                 | 16              |
| Get Outer Here       | [6-8](#_6-8)   | 19   | 9 / 516          | 6 / 8             | 13 / 14         |
| Get Outer Here       | [6-9](#_6-9)   | 17   | 2 / 68           | 5                 | 13              |
| Get Outer Here       | [6-10](#_6-10) | 20   | 10 / 660         | 5 / 7             | 11 / 17         |
| Get Outer Here       | [6-11](#_6-11) | 20   | 6 / 672          | 7 / 8             | 11 / 17         |
| Get Outer Here       | [6-12](#_6-12) | 23   | 3 / 4936         | 8 / 9             | 14 / 16         |

# Them Dots (World 1)

| World     | Level          | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| --------- | -------------- | ---- | ---------------- | ----------------- | --------------- |
| Them Dots | [1-1](#_1-1)   | 4    | 1 / 1            | 0                 | 0               |
| Them Dots | [1-2](#_1-2)   | 5    | 1 / 1            | 0                 | 4               |
| Them Dots | [1-3](#_1-3)   | 6    | 1 / 4            | 1                 | 4               |
| Them Dots | [1-4](#_1-4)   | 7    | 1 / 3            | 0                 | 6               |
| Them Dots | [1-5](#_1-5)   | 5    | 1 / 3            | 1                 | 4               |
| Them Dots | [1-6](#_1-6)   | 9    | 2 / 22           | 3                 | 6               |
| Them Dots | [1-7](#_1-7)   | 8    | 2 / 12           | 3 / 4             | 3 / 7           |
| Them Dots | [1-8](#_1-8)   | 8    | 1 / 7            | 3                 | 7               |
| Them Dots | [1-9](#_1-9)   | 12   | 1 / 30           | 2                 | 9               |
| Them Dots | [1-10](#_1-10) | 11   | 1 / 19           | 3                 | 9               |
| Them Dots | [1-11](#_1-11) | 14   | 1 / 105          | 5                 | 11              |
| Them Dots | [1-12](#_1-12) | 14   | 8 / 105          | 5 / 7             | 9 / 12          |

## 1-1

| World     | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| --------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Them Dots | [1-1](#_1-1) | 4    | 1 / 1            | 0                 | 0               |

    .......
    .xooot.
    .......

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-1-1.GIF" />

## 1-2

| World     | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| --------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Them Dots | [1-2](#_1-2) | 5    | 1 / 1            | 0                 | 4               |

    ...o..t
    .x...o.
    ...o.o.

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-1-2.GIF" />

## 1-3

| World     | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| --------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Them Dots | [1-3](#_1-3) | 6    | 1 / 4            | 1                 | 4               |

    o.o..o
    o.xt.o
    ......

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-1-3.GIF" />

## 1-4

| World     | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| --------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Them Dots | [1-4](#_1-4) | 7    | 1 / 3            | 0                 | 6               |

    .o.o...
    .x.....
    .t.o.o.
    .o...o.

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-1-4.GIF" />

## 1-5

| World     | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| --------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Them Dots | [1-5](#_1-5) | 5    | 1 / 3            | 1                 | 4               |

    o.o....
    .......
    o.o.x.t

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-1-5.GIF" />

## 1-6

| World     | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| --------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Them Dots | [1-6](#_1-6) | 9    | 2 / 22           | 3                 | 6               |

    o..o.o.
    ox.o.ot
    ...o.o.
    .......

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-1-6.GIF" />

## 1-7

| World     | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| --------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Them Dots | [1-7](#_1-7) | 8    | 2 / 12           | 3 / 4             | 3 / 7           |

    x.ooo
    .....
    o.ooo
    t....

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-1-7.GIF" />

## 1-8

| World     | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| --------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Them Dots | [1-8](#_1-8) | 8    | 1 / 7            | 3                 | 7               |

    ...oo
    x.o..
    o.ooo
    t....

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-1-8.GIF" />

## 1-9

| World     | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| --------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Them Dots | [1-9](#_1-9) | 12   | 1 / 30           | 2                 | 9               |

    oo..o.o
    ox.oto.
    .......
    .o.o.oo

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-1-9.GIF" />

## 1-10

| World     | Level          | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| --------- | -------------- | ---- | ---------------- | ----------------- | --------------- |
| Them Dots | [1-10](#_1-10) | 11   | 1 / 19           | 3                 | 9               |

    .o....o
    .x.ooo.
    .o.....
    .t.oooo

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-1-10.GIF" />

## 1-11

| World     | Level          | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| --------- | -------------- | ---- | ---------------- | ----------------- | --------------- |
| Them Dots | [1-11](#_1-11) | 14   | 1 / 105          | 5                 | 11              |

    oo..o.o
    ox.ooo.
    .o.....
    .t.oooo

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-1-11.GIF" />

## 1-12

| World     | Level          | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| --------- | -------------- | ---- | ---------------- | ----------------- | --------------- |
| Them Dots | [1-12](#_1-12) | 14   | 8 / 105          | 5 / 7             | 9 / 12          |

    oo..o.o
    ox.ooo.
    .t.....
    .o.oooo

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-1-12.GIF" />

# A Spring in your Hop (World 2)

| World                | Level          | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------------------- | -------------- | ---- | ---------------- | ----------------- | --------------- |
| A Spring in your Hop | [2-1](#_2-1)   | 5    | 1 / 4            | 1                 | 3               |
| A Spring in your Hop | [2-2](#_2-2)   | 8    | 1 / 4            | 1                 | 5               |
| A Spring in your Hop | [2-3](#_2-3)   | 11   | 1 / 11           | 1                 | 8               |
| A Spring in your Hop | [2-4](#_2-4)   | 12   | 1 / 12           | 2                 | 9               |
| A Spring in your Hop | [2-5](#_2-5)   | 11   | 1 / 17           | 3                 | 10              |
| A Spring in your Hop | [2-6](#_2-6)   | 12   | 1 / 41           | 4                 | 7               |
| A Spring in your Hop | [2-7](#_2-7)   | 13   | 2 / 45           | 3                 | 9               |
| A Spring in your Hop | [2-8](#_2-8)   | 15   | 2 / 125          | 4                 | 7 / 10          |
| A Spring in your Hop | [2-9](#_2-9)   | 18   | 1 / 147          | 5                 | 10              |
| A Spring in your Hop | [2-10](#_2-10) | 19   | 7 / 286          | 5 / 8             | 17              |
| A Spring in your Hop | [2-11](#_2-11) | 20   | 5 / 671          | 6 / 7             | 13 / 16         |
| A Spring in your Hop | [2-12](#_2-12) | 23   | 4 / 1738         | 7 / 9             | 11 / 14         |

## 2-1

| World                | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------------------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| A Spring in your Hop | [2-1](#_2-1) | 5    | 1 / 4            | 1                 | 3               |

    ooo
    oxt

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-2-1.GIF" />

## 2-2

| World                | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------------------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| A Spring in your Hop | [2-2](#_2-2) | 8    | 1 / 4            | 1                 | 5               |

    oo....o
    o.o.o..
    .x..o.t

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-2-2.GIF" />

## 2-3

| World                | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------------------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| A Spring in your Hop | [2-3](#_2-3) | 11   | 1 / 11           | 1                 | 8               |

    .ot.o.o
    x..o...
    .o.oo..
    .o.o..o

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-2-3.GIF" />

## 2-4

| World                | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------------------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| A Spring in your Hop | [2-4](#_2-4) | 12   | 1 / 12           | 2                 | 9               |

    .o.o..o
    ooo..t.
    x.o....
    o.....o
    .o...o.

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-2-4.GIF" />

## 2-5

| World                | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------------------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| A Spring in your Hop | [2-5](#_2-5) | 11   | 1 / 17           | 3                 | 10              |

    o..o..
    .oo.ot
    xooo..
    o...o.

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-2-5.GIF" />

## 2-6

| World                | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------------------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| A Spring in your Hop | [2-6](#_2-6) | 12   | 1 / 41           | 4                 | 7               |

    .o.o..o
    .o.o.t.
    xooo..o
    o.....o

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-2-6.GIF" />

## 2-7

| World                | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------------------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| A Spring in your Hop | [2-7](#_2-7) | 13   | 2 / 45           | 3                 | 9               |

    ..o.o..o
    o.oo..t.
    oxoo...o
    .o.....o

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-2-7.GIF" />

## 2-8

| World                | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------------------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| A Spring in your Hop | [2-8](#_2-8) | 15   | 2 / 125          | 4                 | 7 / 10          |

    .o..o.o.
    o.o.o.o.
    oxt.o..o
    oo....oo

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-2-8.GIF" />

## 2-9

| World                | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------------------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| A Spring in your Hop | [2-9](#_2-9) | 18   | 1 / 147          | 5                 | 10              |

    o.....oo
    oo.o.ooo
    ox.to.o.
    ...o.o.o
    .o.o....

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-2-9.GIF" />

## 2-10

| World                | Level          | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------------------- | -------------- | ---- | ---------------- | ----------------- | --------------- |
| A Spring in your Hop | [2-10](#_2-10) | 19   | 7 / 286          | 5 / 8             | 17              |

    ......o.o..
    oo...oo..t.
    o....o..oo.
    .o..o......
    ox.oo......
    oo.o.......

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-2-10.GIF" />

## 2-11

| World                | Level          | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------------------- | -------------- | ---- | ---------------- | ----------------- | --------------- |
| A Spring in your Hop | [2-11](#_2-11) | 20   | 5 / 671          | 6 / 7             | 13 / 16         |

    ...o..o...
    oo.o.oo.to
    o.....o..o
    .o..o.....
    ox.oo.....
    oo.o......

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-2-11.GIF" />

## 2-12

| World                | Level          | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------------------- | -------------- | ---- | ---------------- | ----------------- | --------------- |
| A Spring in your Hop | [2-12](#_2-12) | 23   | 4 / 1738         | 7 / 9             | 11 / 14         |

    ...o..o.oo
    oo.o.oo.xo
    o.....o..o
    o...oo....
    ot.ooo....
    oo........

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-2-12.GIF" />

# That's Just Beachy (World 3)

| World              | Level          | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| ------------------ | -------------- | ---- | ---------------- | ----------------- | --------------- |
| That's Just Beachy | [3-1](#_3-1)   | 7    | 2 / 3            | 1                 | 6               |
| That's Just Beachy | [3-2](#_3-2)   | 8    | 3 / 11           | 2 / 3             | 5 / 6           |
| That's Just Beachy | [3-3](#_3-3)   | 10   | 2 / 6            | 2 / 3             | 8               |
| That's Just Beachy | [3-4](#_3-4)   | 13   | 3 / 20           | 2 / 3             | 11              |
| That's Just Beachy | [3-5](#_3-5)   | 10   | 2 / 7            | 2                 | 8               |
| That's Just Beachy | [3-6](#_3-6)   | 14   | 2 / 12           | 3                 | 12              |
| That's Just Beachy | [3-7](#_3-7)   | 16   | 1 / 24           | 3                 | 12              |
| That's Just Beachy | [3-8](#_3-8)   | 14   | 2 / 44           | 3 / 4             | 11              |
| That's Just Beachy | [3-9](#_3-9)   | 14   | 1 / 8            | 1                 | 11              |
| That's Just Beachy | [3-10](#_3-10) | 23   | 17 / 399         | 5 / 10            | 11 / 19         |
| That's Just Beachy | [3-11](#_3-11) | 20   | 6 / 582          | 5 / 8             | 13 / 15         |
| That's Just Beachy | [3-12](#_3-12) | 19   | 4 / 610          | 9 / 10            | 17              |

## 3-1

| World              | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| ------------------ | ------------ | ---- | ---------------- | ----------------- | --------------- |
| That's Just Beachy | [3-1](#_3-1) | 7    | 2 / 3            | 1                 | 6               |

    ....o..o
    o.x.o.t.
    o......o

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-3-1.GIF" />

## 3-2

| World              | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| ------------------ | ------------ | ---- | ---------------- | ----------------- | --------------- |
| That's Just Beachy | [3-2](#_3-2) | 8    | 3 / 11           | 2 / 3             | 5 / 6           |

    .o...o.
    .o.o.o.
    .x.....
    .o.o.t.

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-3-2.GIF" />

## 3-3

| World              | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| ------------------ | ------------ | ---- | ---------------- | ----------------- | --------------- |
| That's Just Beachy | [3-3](#_3-3) | 10   | 2 / 6            | 2 / 3             | 8               |

    o..ooo.
    o...o.t
    ...o.o.
    .x..o..

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-3-3.GIF" />

## 3-4

| World              | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| ------------------ | ------------ | ---- | ---------------- | ----------------- | --------------- |
| That's Just Beachy | [3-4](#_3-4) | 13   | 3 / 20           | 2 / 3             | 11              |

    o.....o
    ...oooo
    ....o.x
    ...o.o.
    ot..o.o

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-3-4.GIF" />

## 3-5

| World              | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| ------------------ | ------------ | ---- | ---------------- | ----------------- | --------------- |
| That's Just Beachy | [3-5](#_3-5) | 10   | 2 / 7            | 2                 | 8               |

    ..oo....o
    .o..o....
    ...x....o
    .oo.o..t.

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-3-5.GIF" />

## 3-6

| World              | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| ------------------ | ------------ | ---- | ---------------- | ----------------- | --------------- |
| That's Just Beachy | [3-6](#_3-6) | 14   | 2 / 12           | 3                 | 12              |

    .o..o....
    ..oo....o
    .....oo..
    ...x.oo.o
    .oo.o..t.

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-3-6.GIF" />

## 3-7

| World              | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| ------------------ | ------------ | ---- | ---------------- | ----------------- | --------------- |
| That's Just Beachy | [3-7](#_3-7) | 16   | 1 / 24           | 3                 | 12              |

    .....ooo.
    ..oo....o
    .o..o....
    ...t.oo.o
    .oooo..x.

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-3-7.GIF" />

## 3-8

| World              | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| ------------------ | ------------ | ---- | ---------------- | ----------------- | --------------- |
| That's Just Beachy | [3-8](#_3-8) | 14   | 2 / 44           | 3 / 4             | 11              |

    ...o.oo....
    ..o...o....
    ...o.x.o.t.
    ...o..o....
    ..o..o.....
    ......oo...

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-3-8.GIF" />

## 3-9

| World              | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| ------------------ | ------------ | ---- | ---------------- | ----------------- | --------------- |
| That's Just Beachy | [3-9](#_3-9) | 14   | 1 / 8            | 1                 | 11              |

    ......oo...
    ..o..o.....
    ...o..o....
    ...o.t.o.x.
    ..o...o....
    ...o.oo....

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-3-9.GIF" />

## 3-10

| World              | Level          | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| ------------------ | -------------- | ---- | ---------------- | ----------------- | --------------- |
| That's Just Beachy | [3-10](#_3-10) | 23   | 17 / 399         | 5 / 10            | 11 / 19         |

    .oo.ooo.o...
    ...ooo.o.t..
    ...oo..oo...
    .o.......o.o
    oxo........o
    oo..........

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-3-10.GIF" />

## 3-11

| World              | Level          | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| ------------------ | -------------- | ---- | ---------------- | ----------------- | --------------- |
| That's Just Beachy | [3-11](#_3-11) | 20   | 6 / 582          | 5 / 8             | 13 / 15         |

    ....oo..o.
    o....o..o.
    .oo.oo.to.
    oox....o.o
    o.o.....oo

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-3-11.GIF" />

## 3-12

| World              | Level          | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| ------------------ | -------------- | ---- | ---------------- | ----------------- | --------------- |
| That's Just Beachy | [3-12](#_3-12) | 19   | 4 / 610          | 9 / 10            | 17              |

    oo....o.o.
    .o.o..oo..
    .t..o.o...
    ox.oo.ooo.
    oo........

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-3-12.GIF" />

# Leaf Me Alone (World 4)

| World         | Level          | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| ------------- | -------------- | ---- | ---------------- | ----------------- | --------------- |
| Leaf Me Alone | [4-1](#_4-1)   | 4    | 1 / 3            | 1                 | 3               |
| Leaf Me Alone | [4-2](#_4-2)   | 10   | 1 / 14           | 1                 | 6               |
| Leaf Me Alone | [4-3](#_4-3)   | 11   | 3 / 24           | 2 / 3             | 8               |
| Leaf Me Alone | [4-4](#_4-4)   | 12   | 1 / 7            | 2                 | 10              |
| Leaf Me Alone | [4-5](#_4-5)   | 15   | 5 / 118          | 5 / 7             | 10 / 13         |
| Leaf Me Alone | [4-6](#_4-6)   | 17   | 4 / 165          | 4 / 5             | 12 / 13         |
| Leaf Me Alone | [4-7](#_4-7)   | 21   | 173 / 4985       | 8 / 13            | 8 / 19          |
| Leaf Me Alone | [4-8](#_4-8)   | 18   | 13 / 265         | 5 / 9             | 11 / 15         |
| Leaf Me Alone | [4-9](#_4-9)   | 22   | 7 / 1579         | 7 / 9             | 13 / 16         |
| Leaf Me Alone | [4-10](#_4-10) | 15   | 1 / 14           | 2                 | 10              |
| Leaf Me Alone | [4-11](#_4-11) | 20   | 41 / 1512        | 7 / 10            | 12 / 18         |
| Leaf Me Alone | [4-12](#_4-12) | 21   | 12 / 1584        | 7 / 9             | 12 / 15         |

## 4-1

| World         | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| ------------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Leaf Me Alone | [4-1](#_4-1) | 4    | 1 / 3            | 1                 | 3               |

    oo.
    oxt

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-4-1.GIF" />

## 4-2

| World         | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| ------------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Leaf Me Alone | [4-2](#_4-2) | 10   | 1 / 14           | 1                 | 6               |

    o.o.o
    ..t.o
    o.xo.
    o.oo.

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-4-2.GIF" />

## 4-3

| World         | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| ------------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Leaf Me Alone | [4-3](#_4-3) | 11   | 3 / 24           | 2 / 3             | 8               |

    o.o..
    o..oo
    t.o.o
    ..xo.
    ..oo.

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-4-3.GIF" />

## 4-4

| World         | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| ------------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Leaf Me Alone | [4-4](#_4-4) | 12   | 1 / 7            | 2                 | 10              |

    .o.o...
    ..o.o..
    xo.o.ot
    ...oo..
    ..o..o.

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-4-4.GIF" />

## 4-5

| World         | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| ------------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Leaf Me Alone | [4-5](#_4-5) | 15   | 5 / 118          | 5 / 7             | 10 / 13         |

    o.o...
    o.o...
    o..ooo
    t.o.o.
    ..xo..
    ..oo.o

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-4-5.GIF" />

## 4-6

| World         | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| ------------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Leaf Me Alone | [4-6](#_4-6) | 17   | 4 / 165          | 4 / 5             | 12 / 13         |

    .o...oo.
    o..o..o.
    o.oxoto.
    ..oo....
    oo..o.o.

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-4-6.GIF" />

## 4-7

| World         | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| ------------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Leaf Me Alone | [4-7](#_4-7) | 21   | 173 / 4985       | 8 / 13            | 8 / 19          |

    o.o....
    o.o....
    o..oooo
    t.o.ooo
    ..xo.oo
    ..oo.oo

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-4-7.GIF" />

## 4-8

| World         | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| ------------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Leaf Me Alone | [4-8](#_4-8) | 18   | 13 / 265         | 5 / 9             | 11 / 15         |

    ooo.oooo
    oo.o.o..
    .xo...ot
    ....oo..
    ...o..o.

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-4-8.GIF" />

## 4-9

| World         | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| ------------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Leaf Me Alone | [4-9](#_4-9) | 22   | 7 / 1579         | 7 / 9             | 13 / 16         |

    ooo..oo..
    ....oo.oo
    o...xto.o
    o.o.oo.o.
    ooo...o..

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-4-9.GIF" />

## 4-10

| World         | Level          | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| ------------- | -------------- | ---- | ---------------- | ----------------- | --------------- |
| Leaf Me Alone | [4-10](#_4-10) | 15   | 1 / 14           | 2                 | 10              |

    ....o.ooo
    ..oo....o
    .o....t..
    ..o..o...
    .o..ooo.x

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-4-10.GIF" />

## 4-11

| World         | Level          | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| ------------- | -------------- | ---- | ---------------- | ----------------- | --------------- |
| Leaf Me Alone | [4-11](#_4-11) | 20   | 41 / 1512        | 7 / 10            | 12 / 18         |

    .o..o....
    ootoo...o
    .o.oo.o..
    ...ooxo..
    oo.o..o.o

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-4-11.GIF" />

## 4-12

| World         | Level          | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| ------------- | -------------- | ---- | ---------------- | ----------------- | --------------- |
| Leaf Me Alone | [4-12](#_4-12) | 21   | 12 / 1584        | 7 / 9             | 12 / 15         |

    .o...oo
    o..o..o
    ..oo...
    .o.oo.o
    o.oxoto
    oo..o.o

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-4-12.GIF" />

# Snow Way (World 5)

| World    | Level          | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------- | -------------- | ---- | ---------------- | ----------------- | --------------- |
| Snow Way | [5-1](#_5-1)   | 4    | 1 / 1            | 0                 | 0               |
| Snow Way | [5-2](#_5-2)   | 7    | 1 / 3            | 1                 | 5               |
| Snow Way | [5-3](#_5-3)   | 10   | 1 / 1            | 0                 | 6               |
| Snow Way | [5-4](#_5-4)   | 8    | 2 / 10           | 2 / 3             | 7               |
| Snow Way | [5-5](#_5-5)   | 16   | 7 / 123          | 4 / 7             | 15              |
| Snow Way | [5-6](#_5-6)   | 16   | 2 / 113          | 5 / 7             | 12              |
| Snow Way | [5-7](#_5-7)   | 17   | 1 / 28           | 2                 | 12              |
| Snow Way | [5-8](#_5-8)   | 18   | 5 / 215          | 5 / 7             | 15 / 16         |
| Snow Way | [5-9](#_5-9)   | 20   | 10 / 1422        | 7 / 11            | 13 / 17         |
| Snow Way | [5-10](#_5-10) | 20   | 2 / 156          | 5 / 6             | 15              |
| Snow Way | [5-11](#_5-11) | 18   | 5 / 316          | 6 / 8             | 13 / 14         |
| Snow Way | [5-12](#_5-12) | 21   | 4 / 120          | 5 / 9             | 17 / 20         |

## 5-1

| World    | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Snow Way | [5-1](#_5-1) | 4    | 1 / 1            | 0                 | 0               |

    .x.
    .o.
    .o.
    .o.
    .t.

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-5-1.GIF" />

## 5-2

| World    | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Snow Way | [5-2](#_5-2) | 7    | 1 / 3            | 1                 | 5               |

    .o.o...
    ..xo.o.
    .o...o.
    ...t...

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-5-2.GIF" />

## 5-3

| World    | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Snow Way | [5-3](#_5-3) | 10   | 1 / 1            | 0                 | 6               |

    xo......
    .oo.....
    ..oo....
    ...oooot

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-5-3.GIF" />

## 5-4

| World    | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Snow Way | [5-4](#_5-4) | 8    | 2 / 10           | 2 / 3             | 7               |

    .o.o.oo
    .o...o.
    .x.o..t

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-5-4.GIF" />

## 5-5

| World    | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Snow Way | [5-5](#_5-5) | 16   | 7 / 123          | 4 / 7             | 15              |

    oo..o.o
    ..o.ooo
    .o.o.xo
    .....oo
    t.oo...

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-5-5.GIF" />

## 5-6

| World    | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Snow Way | [5-6](#_5-6) | 16   | 2 / 113          | 5 / 7             | 12              |

    ...o..o
    .o.oooo
    o.o..t.
    x...o.o
    .oo.o.o

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-5-6.GIF" />

## 5-7

| World    | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Snow Way | [5-7](#_5-7) | 17   | 1 / 28           | 2                 | 12              |

    ....o.o...
    ..o.o....o
    oo...oo...
    ....o.to..
    ox.....o.o
    ..o.o.....

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-5-7.GIF" />

## 5-8

| World    | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Snow Way | [5-8](#_5-8) | 18   | 5 / 215          | 5 / 7             | 15 / 16         |

    .oo.o.o
    ..o.ooo
    .o.t.o.
    oo...xo
    .....oo
    oo.....

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-5-8.GIF" />

## 5-9

| World    | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Snow Way | [5-9](#_5-9) | 20   | 10 / 1422        | 7 / 11            | 13 / 17         |

    .oo.o.t
    ..o.ooo
    .o...o.
    oo.o.xo
    .o.o.oo
    oo.....

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-5-9.GIF" />

## 5-10

| World    | Level          | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------- | -------------- | ---- | ---------------- | ----------------- | --------------- |
| Snow Way | [5-10](#_5-10) | 20   | 2 / 156          | 5 / 6             | 15              |

    ...o.o.o...
    ...t.o..o..
    ..o..oo...o
    ..oo..o....
    ox......o.o
    oo.o...o...

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-5-10.GIF" />

## 5-11

| World    | Level          | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------- | -------------- | ---- | ---------------- | ----------------- | --------------- |
| Snow Way | [5-11](#_5-11) | 18   | 5 / 316          | 6 / 8             | 13 / 14         |

    o.o........
    ..x........
    o..o.o..o..
    .too....o.o
    ooo.....o.o
    .o......o..

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-5-11.GIF" />

## 5-12

| World    | Level          | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------- | -------------- | ---- | ---------------- | ----------------- | --------------- |
| Snow Way | [5-12](#_5-12) | 21   | 4 / 120          | 5 / 9             | 17 / 20         |

    ...o......o...
    ......t.o..o..
    ....oo..o....o
    ...oooo.......
    oox........o.o
    ooo.......o...

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-5-12.GIF" />

# Get Outer Here (World 6)

| World          | Level          | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------------- | -------------- | ---- | ---------------- | ----------------- | --------------- |
| Get Outer Here | [6-1](#_6-1)   | 7    | 1 / 3            | 1                 | 6               |
| Get Outer Here | [6-2](#_6-2)   | 9    | 1 / 18           | 3                 | 6               |
| Get Outer Here | [6-3](#_6-3)   | 14   | 3 / 20           | 2 / 3             | 9               |
| Get Outer Here | [6-4](#_6-4)   | 15   | 1 / 87           | 5                 | 11              |
| Get Outer Here | [6-5](#_6-5)   | 20   | 5 / 142          | 5 / 6             | 14 / 17         |
| Get Outer Here | [6-6](#_6-6)   | 20   | 2 / 166          | 4 / 5             | 16              |
| Get Outer Here | [6-7](#_6-7)   | 18   | 2 / 68           | 4                 | 16              |
| Get Outer Here | [6-8](#_6-8)   | 19   | 9 / 516          | 6 / 8             | 13 / 14         |
| Get Outer Here | [6-9](#_6-9)   | 17   | 2 / 68           | 5                 | 13              |
| Get Outer Here | [6-10](#_6-10) | 20   | 10 / 660         | 5 / 7             | 11 / 17         |
| Get Outer Here | [6-11](#_6-11) | 20   | 6 / 672          | 7 / 8             | 11 / 17         |
| Get Outer Here | [6-12](#_6-12) | 23   | 3 / 4936         | 8 / 9             | 14 / 16         |

## 6-1

| World          | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Get Outer Here | [6-1](#_6-1) | 7    | 1 / 3            | 1                 | 6               |

    .oo..
    xo.ot
    ..oo.

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-6-1.GIF" />

## 6-2

| World          | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Get Outer Here | [6-2](#_6-2) | 9    | 1 / 18           | 3                 | 6               |

    .ooo.
    xooot
    ..oo.

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-6-2.GIF" />

## 6-3

| World          | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Get Outer Here | [6-3](#_6-3) | 14   | 3 / 20           | 2 / 3             | 9               |

    oo...o.o
    o.......
    .o...x.o
    o.o..o..
    .t.ooo..

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-6-3.GIF" />

## 6-4

| World          | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Get Outer Here | [6-4](#_6-4) | 15   | 1 / 87           | 5                 | 11              |

    .x.o.t.
    ..o.oo.
    .o.o..o
    ..oo.o.
    .o.oo.o

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-6-4.GIF" />

## 6-5

| World          | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Get Outer Here | [6-5](#_6-5) | 20   | 5 / 142          | 5 / 6             | 14 / 17         |

    .o..o.o..
    x.o......
    oo..ooooo
    ..oo.....
    o...oto.o
    ...o...o.

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-6-5.GIF" />

## 6-6

| World          | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Get Outer Here | [6-6](#_6-6) | 20   | 2 / 166          | 4 / 5             | 16              |

    ..o.o.o..
    x.o.ooooo
    ..oo.....
    o...oto.o
    ...o...o.
    .....oo..

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-6-6.GIF" />

## 6-7

| World          | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Get Outer Here | [6-7](#_6-7) | 18   | 2 / 68           | 4                 | 16              |

    ..o....o.
    xoo...oo.
    ...oo....
    o.o..to..
    ..oo.oo..
    .o..o....

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-6-7.GIF" />

## 6-8

| World          | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Get Outer Here | [6-8](#_6-8) | 19   | 9 / 516          | 6 / 8             | 13 / 14         |

    oo...o.o
    o.o....o
    .o.o.x.o
    o.o..o..
    .t.ooo..
    o.o.....

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-6-8.GIF" />

## 6-9

| World          | Level        | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------------- | ------------ | ---- | ---------------- | ----------------- | --------------- |
| Get Outer Here | [6-9](#_6-9) | 17   | 2 / 68           | 5                 | 13              |

    oo...o..o
    o.oo.o...
    .ox......
    .o...o...
    ...oto...
    ...oo...o

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-6-9.GIF" />

## 6-10

| World          | Level          | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------------- | -------------- | ---- | ---------------- | ----------------- | --------------- |
| Get Outer Here | [6-10](#_6-10) | 20   | 10 / 660         | 5 / 7             | 11 / 17         |

    o..o....
    o..x.oo.
    ...o..oo
    ooo..o..
    .ooo.t..
    o.o..o.o

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-6-10.GIF" />

## 6-11

| World          | Level          | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------------- | -------------- | ---- | ---------------- | ----------------- | --------------- |
| Get Outer Here | [6-11](#_6-11) | 20   | 6 / 672          | 7 / 8             | 11 / 17         |

    o.....o..
    o...oo..o
    o...ot..o
    ....o.o..
    ox.o..o.o
    .o.o..o.o

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-6-11.GIF" />

## 6-12

| World          | Level          | Dots | Paths (wins/all) | Choices (min/max) | Turns (min/max) |
| -------------- | -------------- | ---- | ---------------- | ----------------- | --------------- |
| Get Outer Here | [6-12](#_6-12) | 23   | 3 / 4936         | 8 / 9             | 14 / 16         |

    ..o.oo.
    .o..o.o
    .o.o..o
    o.o.oo.
    ..oo.o.
    ox.o.t.
    .o.oo.o

<img src= "_images/dothop/speedrun_clips/dothop_speedrun-6-12.GIF" />
