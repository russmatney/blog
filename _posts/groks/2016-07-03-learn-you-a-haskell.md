---
layout: post
title: 'GROK: Learn You A Haskell For Great Good'
categories:
- GROKs
tags:
- Haskell
type: post
---

###GROK: [*Learn You A Haskell For Great Good*](http://learnyouahaskell.com/):

---

##Goal(s)

- Expand understanding of programming, functional languages, abstractions, state

##Resource

- First remember hearing about it from [Adam](http://agibbons.com/) and [Paul](http://pmn.org/).
- author: Miran Lipovača
  - Could not find a github or otherwise social media profile (outside of good reads)
  - Also built [BigBassTabs.com](http://www.bigbasstabs.com/)
- source: His own publication, as a book and pdf
  - shared under a creative commons license, available widely from many other sources, including several github repos

##Offload

Offload:

###Chapter 1

####1.1
  
  Miran mentions that he learned Haskell by piecing it together from all over the web.
  He gives context that this is just another piece to help others find a way they like.
  He states his goal as helping himself learn haskell by writing this.
  He says he failed to learn haskell twice before it 'clicked'.

####1.2

Haskell

- purely functional programming language
  - no side-effects 
- Referential Transparency
  - allows compiler to reason about the program's behavior, and you to 'prove' that a function is correct
- Haskell is Lazy
  - instructions, just a series of data transformations, only used when needed
- statically typed with type inference
- started work in 1987, stable release in 2003
- GHC - Glasgow Haskell Compiler
- ghci - i - interactive

###Chapter 2

####2.1

Infix function:
  - func is sandwiched between it's two params (i.e. 3 * 5)

prefix function - func comes before params
  - succ 9 * 10 \= succ (9 * 10)
  - can call prefix funcs as infix w/ back ticks
    - "div 10 5 == 10 `div` 5"

####2.2

Ifs are expressions - they always return something.

Functions can have ' (quotes) for naming.
Functions can't start with uppercase letters.

Functions with no params can be called a _definition_ or a _name_

####2.3 Lists

`let` in ghci to set var names (let a = 1)

Lists are homogenous.

Strings are lists of chars, so you can use list funcs on them
  - "hello" is syntax for ['h','e','l','l','o']

  - `[1,2,3,4] ++ [5]` has to walk the whole first array
  - `5:[1,2,3,4]` prepends instantly

  - `[1,2,3]` is syntax for `1:2:3:[]`

  - `"derp" !! 3` returns the elem at the index of the array `'p'`

Lists can be compared lexicographically

#### 2.4

Haskell allows for infinite lists (b/c it's lazy)
  - first 24 instances of 13: `take 24 [13,26..]`
    - take takes a count and a list, returns up to the count
    - the range takes a pattern step after the comma to determine how to populate the list
  - `take 12 (cycle "LOL ")`

Comprehensions
  - `[x*2 | x <- [1..10], x `mod` 2 == 0]`
  - filtering: weeding out lists w/ predicates

Note the ’ to denote that this is a modified version of the func
  - `length’ xs = sum [1 | _ <- xs]`

`removeNonUppercase st = [ c | c <- st, c ‘elem‘ [’A’..’Z’]]`

####2.*

Tuples work as expected

###Chapter 3: Types

Type definition ex:
  ```
  addThree :: Int -> Int -> Int -> Int
  addThree x y z = x + y + z
  ```

- () is a type that can only have a single value: ()

Type Variables and Polymorphic Functions
  - Don't use behavior specified by the types passed into them

- Type Classes and Class Constraints

- Type Annotations
  - `read "5" :: Float`

###Chapter 4
###Chapter 5
###Chapter 6
###Chapter 7
###Chapter 8
###Chapter 9
###Chapter 10
###Chapter 11
###Chapter 12
###Chapter 13
###Chapter 14

##Kickers

  - #TODO: look into haskell vim/emacs plugins
  - #TODO: research some: 'proving a function'
  - #TODO: general overview: who and when and why were languages designed (history of programming languages)
  - #TODO: compare things lexicographically?
  - #TODO: start working with elixir
