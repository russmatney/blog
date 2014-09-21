---
layout: post
title: 'Angular TDD+CRUD Example Part 1'
categories:
- Techsposure
tags:
- Angularjs
- Clean Code
type: post
published: false
---

###*TL;DR*:

After building with AngularJS for over a year,
I've developed a few opinions on what belongs in a Controller, Service, Resource, and Directive.
I've also built up a knack for how to deal with common Unit testing problems between some of these pieces.
This post is the first in a series that walks through the Angular architecture that I like.

---

##Getting started

slush angular-playground
(add tests, karma to slush-angular playground)

##A mock endpoint

need something to toy with. CRUD what? Little Koa app? Golang?

Endpoints are DRYest when wrapped up in an angular `$resource`.
I find using `$http` directly more readable,
but `$resource`s are very powerful and easier to tweak once you get the hang of it.

##GET that service

Services handle your app's logic
and should present convenient, pre-built data structures for your controllers.

The most common use-case for this that I've seen is building a more complete object out of data from a database.
Say we have a shopping cart with an array of reference_ids,
and an array of items waiting to be attached to that cart.
The items should be attached at the `$resource` or `$service` level,
and definitely NOT in a `$controller`.
If you don't need shopping_cart.items to be populated every time,
just expose two functions in your service rather than one.

```
getCart(id)
getBuiltCart(id)
```

##I'm a Controller

Controllers should be small!

No need for doubly nested functions or anything complex –
if you find yourself adding alot of complexity to a controller,
it's likely your logic belongs in a service (or factory),
which also means that logic will be DRY and reusable in other controllers in your app.

##Directives are glorious
