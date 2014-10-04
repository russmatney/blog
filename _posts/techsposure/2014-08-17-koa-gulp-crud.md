---
layout: post
title: 'Basic Koa API + Supertest'
categories:
- Techsposure
tags:
- Koa
- Nodejs
type: post
---

###*TL;DR*:

A step-by-step walkthrough for building a Koa app
and getting a few endpoint-level tests covering your CRUD
from the beginning.

All code in this post can be found on [github](https://github.com/russmatney/koa-gulp-crud).

---

##A bit on generators

JavaScript has a few new things coming in ECMA 6, one of which is
a new kind of function called a generator.
I hope to get into the details of how generators work in another post,
but for now, you can recognize a generator by the `*` added to the
function signature, and the use of a new keyword `yield`:

```
var doWork = function *() {
	console.log("I am a generator");
	var data = yield getData();
	console.log(data);
}
```

Generators allow functions to be broken into pieces
that can run with more control than before.
One big advantage is dealing with asynchronous control flow.
In javascript and especially Node, we're used to using
the callback pattern or promises to wait for some async promise
to run before moving on.

Generators simplify that – if handled properly,
the above function will 'yield' when we hit `getData()`.
Control flow will buzz along elsewhere, but eventually
return the output of `getData()` to our `data` var
and continue walking through `doWork()`. There are some
details to getting this to work as described, but imagine
how much cleaner a `yield` is than callbacks of doom
or importing a promise library.

This is just one use of generators; for more details on
what exactly is going on under the hood, I learned a ton
from [David Walsh's series](http://davidwalsh.name/es6-generators).

##co

Now we know generators can give us a new way to control
async processes in our code. Sweet. So how do we implement them?

Rather than implement them from scratch, we're going to climb
up on the shoulders of giants and use some clever abstractions. [TJ
Holowaychuk](https://github.com/visionmedia)'s [`co`
library](https://github.com/visionmedia/co) lets us treat
callbacks or promises as generators. ([One example in the co
docs](https://github.com/visionmedia/co#cofn)).

I recommend looking at co and toying with it on your own.
For the purposes of this post, I'm going to keep trucking
towards Koa to try to keep things concise.

##Koa

co makes mixing generators and existing packages as easy as adding a
wrapper. So what's Koa?

You can think of [KoaJS](http://koajs.com/) as a successor to
[ExpressJS](http://expressjs.com/), both in spirt and in
maintainer. Koa is a framework for building Node apps that
takes full advantages of generators. It's super slim, and it makes
some interesting design decisions to keep your code clean.

One of these design decisions is the use of what Koa calls 'context'.
Rather than the typical node fn(request, response){} signature,
Koa combines the request and response into the context of the function.
`req.body` now becomes `this.body`. `res.query` now becomes `this.query`.

You can check out the Koa docs for more info, but if you're like me,
this all means nothing until you've thrown an app or two together.
Let's dive in.

##Perfect Harmony

Using generators (and therefore co or koa) require a bit of setup –
you need a version of Node that supports it (v0.11.x) and you need to
use the `--harmony` flag to include the new ECMA 6 features.

I recommend using [nvm](https://github.com/creationix/nvm) to install
and run the latest version of node. I'm using v0.11.11 to run this project.

The `--harmony` flag can be added to the end of any `node` command you run on
the command line, but what we'll do is add a 'start' script to our package.json,
and just use `npm start` to make the magic happen.

##A lil baby Koa app

server.js
koa version
how to run it (node --harmony)

##Big Gulps, huh?

gulpfile.js
package.json npm start

##Start your testing engines

test/endpoint.js
gulpfile test edits

##Your failing endpoint test

test/endpoint

##Your Just-Approved Endpoint

server.js

##A healthy refactor

server.js
api/endpoint.js
gulpfile update

##Lather, rinse, repeat.

Next steps:

  - perhaps a database?
