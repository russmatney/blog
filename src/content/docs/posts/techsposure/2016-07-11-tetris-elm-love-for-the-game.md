---
title: "'Tetris, Elm, and Love for the Game'"
date: 2016-07-11
category: techsposure
tags: [Techsposure, Elm, Tetris, Code, tech]
---



###*TL;DR*: Was reading some Elm, found a tetris remake with a file that make me smile

---

A quick shout out to [@jcollard](github.com/jcollard) for the code style in this file (link to source below):


```elm
module Tetromino where

import Location (..)
import Util

-- A Tetromino is a list of Locations. By definition, a valid tetromino
-- should contain exactly 4 different locations
type Tetromino = [Location]

-- A line piece
-- ****
line : Tetromino
line = [(0,0), (1,0), (2,0), (3,0)]

-- A square piece
-- **
-- **
square : Tetromino
square = [(0,0), (1,0),
          (0,1), (1,1)]
-- A Z piece
-- **
--  **
zpiece : Tetromino
zpiece = [(0,0), (1,0),
                 (1,1), (2,1)]
-- An S piece
--  **
-- **
spiece : Tetromino
spiece = [       (1,0), (2,0),
          (0,1), (1,1)]

-- A J piece
--  *
--  *
-- **
jpiece : Tetromino
jpiece = [       (1,0),
                 (1,1),
          (0,2), (1,2)]

-- An L piece
-- *
-- *
-- **
lpiece : Tetromino
lpiece = [(0,0),
          (0,1),
          (0,2), (1,2)]

-- A T piece
-- ***
--  *
tpiece : Tetromino
tpiece = [(0,0), (1,0), (2,0),
                 (1,1)]
```

[Original source file](https://github.com/jcollard/elmtris/blob/master/src/Tetromino.elm).

The comments are one thing, but that's some some serious love for the game when the code is shaped like the pieces.
