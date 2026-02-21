---
title: "Games Portfolio"
category: portfolio
---

# Games Portfolio

> I started solo-deving on games full time in July of 2022.
>
> My first year was all about game jams - I mostly worked in one monorepo
> (russmatney/dino), from which I submitted 10+ (tiny) games and worked as many
> tools and prototypes.
>
> In my second year, I pushed for steam releases. `Dot Hop` launched on
> March 2024, and `Dino` launched a few months later in June.
>
> In the second half of 2024, I pushed for more collaboration! I led Team
> Moonstorm through the launch of `Rapid Eye Madness` in late October as part of
> the Indie Game Academy's Level 3 program.
>
> In November, I built `Carving with Care` with Cat Tale Games - more to come on
> that in Q1 2025!

# Launched

Below I'll dig into each game, grouped by team.

## Danger Russ Games

Once a silly name for my solo-dev work, now a fully fledge one-person LLC!

> Beyond the games listed here, there are more games and prototypes available on
> [my itch.io page](https://russmatney.itch.io).

### Dot Hop

A short puzzle game. Try to find a path connecting all the dots!

<video controls width="80%" height="400px">
    <source
    src="_images/dothop/dothop_gameplay_feb_10_2024.mp4"
    type="video/mp4"
    />
</video>

<figcaption><em>Some clips from a few weeks before the Steam launch.</em></figcaption>

Dot Hop launched with 72 puzzles (12 on 6 'worlds'). I hope to increase this
number on a per season basis, once I'm past the mobile release.

Originally implemented as a PuzzleScript game called [Flower Eater](https://russmatney.itch.io/flower-eater).

```
Status: Launched
Platform: Steam
Date: March 2024
Next step: Mobile release
Engine: Godot
```

> I've covered Dot Hop in video as well:
>
> - [A Tour of Dot Hop](https://youtu.be/yfdHTPWyWvA)
> - [Dot Hop Game Trailer](https://youtu.be/fxT0u9VVcIg)

[Dot Hop on Steam](https://store.steampowered.com/app/2779710/Dot_Hop)
| [Dot Hop on itch.io](https://russmatney.itch.io/dothop)
| [Dot Hop code on github](https://github.com/russmatney/dothop)
| [Dot Hop docs (wip)](https://russmatney.github.io/dothop)

<iframe src="https://store.steampowered.com/widget/2779710/" frameborder="0" width="646" height="190"></iframe>

### Dino

Dino started as a Godot monorepo. It has featured several games and prototypes,
including a small metroidvania
([hatbot](https://russmatney.itch.io/mvania19-web)), some platformer and top
down experiments ([shirt](https://russmatney.itch.io/shirt),
[spike](https://russmatney.itch.io/spike), etc), a short beat em up ([super
elevator level](https://russmatney.itch.io/super-elevator-level-web)), and even an
early version of Dot Hop.

> I covered this early phase of Dino in a video devlog: [Dino 'So Far'](https://youtu.be/9cyAnNLGrZI)

In the build-up to Dino's launch, the mechanics from these games were
refactored into reusable components. Dino launched with a few game modes
supported by some basic procedural level generation.

<video controls width="80%" height="400px">
    <source
    src="_images/dino/dino-first-villagers 2024-05-07 19-35.mp4"
    type="video/mp4"
    />
</video>

<figcaption><em>An generated snowy village experiment in Dino.</em></figcaption>

Next up: creating and honing the content - I'm recreating some of the original
game jam ideas using the proc-gen system, and toying with various player and
enemy mechanics.

Dino is a convenient sandbox for prototyping both mechanics and game libraries.
It had several Godot addons, which have so far been
broken out into [Log.gd](https://github.com/russmatney/log.gd) and [Bones](https://github.com/russmatney/bones).

```
Status: Launched
Platform: Steam
Date: June 2024
Next step: More content (level editor, enemies, player abilities, etc)
Engine: Godot
```

[Dino on Steam](https://store.steampowered.com/app/2589550/Dino)
| [Dino on itch.io](https://russmatney.itch.io/dino)
| [Dino code on github](https://github.com/russmatney/dino)
| [Dino docs site](https://russmatney.github.io/dino)

<iframe src="https://store.steampowered.com/widget/2589550/" frameborder="0" width="646" height="190"></iframe>

### Web Games

A few small web games. For now, a wordle clone.

I hope to build up some more interesting games here soon! For now, play all the
wordle you want.

```
Status: Live
Platform: Browser
Date: November 2024
Next step: More games!
Tools: ClojureScript/React
```

[Web Games](https://games.russmatney.com/)

## Moonstorm Clerics

[The Moonstorm Clerics](https://moonstorm-clerics.carrd.co) were formed as part of the Indie Game Academy's 7th Level
3 Cohort. Rapid Eye Madness is our major release, but we're also quite proud of
how Wicked Peak turned out after only a few days of development.

### Rapid Eye Madness

Rapid Eye Madness is a cozy horror microgame bonanza. See if you can survive all
the microgames, and keep playing to unlock all the audiolog-lore.

<video controls width="80%" height="400px">
    <source
    src="_images/rapid-eye-madness/microgame tvs and playthroughs 2024-10-08 at 16.51.24.mp4"
    type="video/mp4"
    />
</video>

<figcaption><em>REM's minigames, all at once.</em></figcaption>

<img src="_images/rapid-eye-madness/REMClip_Mix_2.gif" alt="REM Mixed gameplay clip" />

```
Status: Launched
Platform: Steam
Date: October 2024
Next step: More microgames!
Engine: Unity
Tools: Wwise
Team: Moonstorm Clerics
```

[R.E.M. on Steam](https://store.steampowered.com/app/3248030/Rapid_Eye_Madness)
| [R.E.M. on itch.io](https://moonstorm-clerics.itch.io/rapid-eye-madness)

<iframe src="https://store.steampowered.com/widget/3248030/" frameborder="0" width="646" height="190"></iframe>

### Wicked Peak

A short platformer with a grapple mechanic, built for IGA's Level 3 weekend
game jam.

<video controls width="80%" height="400px">
    <source
    src="_images/wicked-peak/prelaunch gameplay 2024-07-16 17-28.mp4"
    type="video/mp4"
    />
</video>

```
Status: Launched
Platform: Itch
Date: July 2024
Engine: Unity
Team: Moonstorm Clerics
```

[Wicked Peak on itch.io](https://moonstorm-clerics.itch.io/wicked-peak)

<iframe frameborder="0" src="https://itch.io/embed/2824176" width="552" height="167"><a href="https://moonstorm-clerics.itch.io/wicked-peak">Wicked Peak by Team Moonstorm, Janguardian, Moss Covered Bones, Malethar, russmatney, TooLoo Studio, André Castro</a></iframe>

## Cat Tale Games

A team formed from connections made across IGA classes in 2024.

### Carving With Care

Inspired by "Strange Horticulture but Wood Carving", Carving with Care is a
cozy rune-carving game. Use your book of runes and careful carving skills to enrich the lives of your customers.

<video controls width="80%" height="400px">
    <source
    src="_images/carving-with-care/carving recording 2024-11-18 18-31.mp4"
    type="video/mp4"
    />
</video>

<figcaption><em>An in-progress clip experimenting with adding wood-shavings while carving.</em></figcaption>

```
Status: Launched
Platform: Itch
Date: November 2024
Engine: Unity
Tools: Spine2d, Yarn, FMOD
Team: Cat Tale Games
```

[Carving With Care on itch.io](https://cattalegames.itch.io/carving-with-care)

<iframe frameborder="0" src="https://itch.io/embed/3103109" width="552" height="167"><a href="https://cat-tale-games.itch.io/carving-with-care">Carving with Care by Cat Tale Games, russmatney, margaretglin, Ariyassa, artmuse62, questionmarc, Leanasta, becdar, Miraiya, Vulspera</a></iframe>

## Antifun Bloc

### Zohran's Run

Play as Zohran across NYC neighborhoods, collecting constituents and listening to their stories.

```
Status: Launched
Platforms: Steam, Itch
Date: November 2025
Engine: Unity
Tools: Ink
Team: Antifun Bloc
```

- [Zohran's Run on Steam](https://store.steampowered.com/app/4072500/Zohrans_Run/)
- [Zohran's Run on Itch.io](https://antifunbloc.itch.io/zrun)

<iframe src="https://store.steampowered.com/widget/4072500/" frameborder="0" width="646" height="190"></iframe>

# In-progress

### Glossolalia

A Godot Metroidvania, coming in 2025.

> Check out my ["Listen" prototype devlog](/devlogs/2024-12-11-glossolalia-listen-prototyping)

<video controls width="80%" height="400px">
    <source
    src="_images/glossolalia/listen prototyping 2024-12-11 13-07.mp4"
    type="video/mp4"
    />
</video>

<video controls width="80%" height="400px">
    <source
    src="_images/glossolalia/listen gym two playthrough 2024-12-11 16-51.mp4"
    type="video/mp4"
    />
</video>

<figcaption><em>An early prototype experimenting with a 'Listen' mechanic.</em></figcaption>

```
Status: In development
Engine: Godot
Tools: FMOD
```

### Blox

So far, Blox is a tetris and puyo-puyo implementation.

The next step is to prototype a few different ideas:

- "Lazy Tetris"
  - removing a the time-pressure component
  - Would a 'clicker' tetris be a fun 'background' game?
- "Balatro-Tetris"
  - Applying deckbuilder roguelike mechanics to some tried and true block-faller
    games
- "Set Tetris"
  - a block faller with rules similar to 'Set'

I'm not making enough time for this project at the moment :/

<video controls width="80%" height="400px">
    <source
    src="_images/blox/editing-while-running-in-editor 2024-06-15 19-01.mp4"
    type="video/mp4"
    />
</video>

<figcaption><em>Tetris blocks falling directly in the godot editor (at @tool-time!)</em></figcaption>

```
Status: Prototype on itch.io
Next step: Prototype Roguelike mechanics
Engine: Godot
```

[Blox on itch.io](https://russmatney.itch.io/blox)
| [Blox code on github](https://github.com/russmatney/blox)

<iframe frameborder="0" src="https://itch.io/embed/2787786" width="552" height="167"><a href="https://russmatney.itch.io/blox">Blox by russmatney</a></iframe>

### Beat Em Up City

For a long time I was calling this "The Hitch-Hiker's Guide to the Beat Em Up",
and other times a "perpetual" beat em up.

The idea is a sort of Animal Crossing <> Beat Em Up - an idea that makes sense
in some ways and is kind of crazy in others. Move to an apartment in the big
city, meet your neighbors, defend your territory, build up your reputation!

<video controls width="80%" height="400px">
    <source
    src="_images/beatemupcity/Peek 2023-08-17 14-21.mp4"
    type="video/mp4"
    />
</video>

<figcaption><em>An early prototype, mostly toying with pixel art look-and-feel.</em></figcaption>

Too early in dev to share much more! Stay tuned.

```
Status: In development
Next step: Hone Beat Em Up fighting mechanics
Engine: Godot
```
