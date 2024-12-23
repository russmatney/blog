# Games


I started solo-deving on games full time in July of 2022.

My first year was all about game jams - I mostly worked in one monorepo, from which I submitted 10+ (tiny) games.

In my second year, I pushed for steam releases. Dot Hop launched on
March 1st 2024, and a Dino a few months later on June 1st.

In the second half of 2024, I collaborated with other game devs,
building Rapid Eye Madness and Carving with Care with two different teams from the Indie Game Academy community.

# Launched

## Danger Russ Games

A silly name for my solo-dev work.

There are more games and prototypes available on [my itch.io page](https://russmatney.itch.io).

### Dot Hop

A short puzzle game. Try to find a path connecting all the dots!

Dot Hop launched with 72 puzzles (12 on 6 'worlds'). I hope to increase this
number on a per season basis, once I'm past the mobile release.

Originally implemented as a PuzzleScript game called Flower Eater.

```
Status: Launched
Platform: Steam
Date: March 2024
Next step: Mobile release
Engine: Godot
```

[Dot Hop on Steam](https://store.steampowered.com/app/2779710/Dot_Hop)
| [Dot Hop on itch.io](https://russmatney.itch.io/dothop)
| [Dot Hop code on github](https://github.com/russmatney/dothop)
| [Dot Hop docs (wip)](https://russmatney.github.io/dothop)

- TODO: add links to youtube devlogs about dothop
- TODO: drop in dothop logo/banner/gifs
- TODO: link to flower eater

### Dino

Dino started as a Godot monorepo. It has featured several games and prototypes,
including a small metroidvania (hatbot), some platformer and top down
experiments (shirt, spike, etc), a short beat em up (super elevator level), and
even an early version of Dot Hop.

In the build-up to Dino's launch, the mechanics from these games were
refactored into reusable components. Dino launched with a few game modes
supported by some basic procedural level generation.

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

- TODO: add links to youtube devlogs about dino
- TODO: drop in dothop logo/banner/gifs
- TODO: in-text links to some of the itch games (e.g. hatbot, super elevator level)

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


### Wicked Peak

A short platformer with a grapple mechanic, built for IGA's Level 3 weekend
game jam.

```
Status: Launched
Platform: Itch
Date: July 2024
Engine: Unity
Team: Moonstorm Clerics
```

[Wicked Peak on itch.io](https://moonstorm-clerics.itch.io/wicked-peak)

## Cat Tale Games

A team formed from connections made across IGA classes in 2024.

### Carving With Care

Inspired by "Strange Horticulture but Wood Carving", Carving with Care is a
cozy rune-carving game. Use your book of runes and careful carving skills to enrich the lives of your customers.

```
Status: Launched
Platform: Itch
Date: November 2024
Engine: Unity
Tools: Spine2d, Yarn, FMOD
Team: Cat Tale Games
```

[Carving With Care on itch.io](https://cattalegames.itch.io/carving-with-care)

# In-progress

### Glossolalia

A Godot Metroidvania, coming in 2025.

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

```
Status: Prototype on itch.io
Next step: Prototype Roguelike mechanics
Engine: Godot
```

[Blox on itch.io](https://russmatney.itch.io/blox)
| [Blox code on github](https://github.com/russmatney/blox)

### Beat Em Up City

For a long time I was calling this "The Hitch-Hiker's Guide to the Beat Em Up",
and other times a "perpetual" beat em up.

The idea is a sort of Animal Crossing <> Beat Em Up - an idea that makes sense
in some ways and is kind of crazy in others. Move to an apartment in the big
city, meet your neighbors, defend your territory, build up your reputation!

Probably too early to share! More to come soon.

```
Status: In development
Next step: Hone Beat Em Up fighting mechanics
Engine: Godot
```
