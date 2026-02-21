---
title: ""'GROK: Haxl from Facebook'""
date: 2016-07-03T00:00-04:00[America/New_York]
category: groks
tags: [GROKs, learning]
---



Two here that are close enough to the same: a github repo and an article on its origins

###GROK: [*Open-sourcing Haxl: A library for Haskell*](https://code.facebook.com/posts/302060973291128/open-sourcing-haxl-a-library-for-haskell/)

###GROK: [*facebook/Haxl (github repo)*](https://github.com/facebook/Haxl)

##Goal

Lately I'm interested in separation of concerns at the app level,
specifically distinguishing task/instruction implementation from a
domain-specific compiler for optimizing batches of those operations (grouping requests, rate-limiting, etc).
This is especially useful in a functional programming mindset because of the
typical emphasis on defining stateless functions.

Facebook has proved itself to be a powerful engineering force (GraphQL, React, etc.).
I've been quite impressed with the literature Facebook's engineering provides
around its projects as well. 
I'm pushing the functional stuff for myself lately (Haskell, Elm).
So this seems like a great project to explore.

##Resource

###Media Outlet

[code.facebook.com (facebook dev blog)](https://code.facebook.com/posts/302060973291128/open-sourcing-haxl-a-library-for-haskell/),
published June 10, 2014

###Authors:

 - Simon Marlow
 - Jon Purdy

###Discovered:

I found a reference to this while looking up a Github issue for graphql.
I was actually looking for information related to merging graphql schemas.
Found and pocketed this along the way. [Issue link](https://github.com/graphql/graphql-js/issues/143#issuecomment-137807156).

##Offload

###Haxl

  - layer between application code and 'data sources'

#####can automatically:

    - group multiple requests into a single one
    - concurrently request data from multiple
    - cache previous requests
  - benefit: sep of concerns: moving optimization out of the application logic 

#####accomplished via:

    - implicit concurrency via Applicative functors
    - implicit caching means soundness and modularity for free

###Implicit Concurrency

  - `numCommonFriends x y = length <$> (intersect <$> friendsOf x <*> friendsOf y)`
    - `<*>` allows for concurrent function application

###Implicit Caching

  - soundness - ensures that changing resources behind the haxl layer don't lead to unpredictable results
  - modularity - call sites don't need to know anything about how other call sites work

##Kickers

- TODO: put together/link well-written FB Tech posts
- TODO: pursue Simon Marlow, Jon Purdy for background, other tech writing, github projects
- TODO: Read source for Haxl
  - learn how soundness is ensured
- TODO: handful of haskell concepts to read about/understand

