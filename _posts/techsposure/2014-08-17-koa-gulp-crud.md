---
layout: post
title: 'Basic Koa API + Supertest'
categories:
- Techsposure
tags:
- Koa
- Nodejs
type: post
published: false
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

```javascript
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

Let's get our app up and running.

Here's the code for a basic http server:

```javascript
//server.js
var koa = require('koa');

var app = module.exports = koa(),
  port = process.env.PORT || 8000,
  env = process.env.NODE_ENV || 'development';

app.listen(port);
console.log('app listening on port: ', port);
```

If we try to run this with `node server.js`,
you should see an error like `SyntaxError: Unexpected token *`.
`node --harmony server.js` should run smoothly (displaying the console.log
above). If you're still seeing the same syntax error,
make sure you're running node v0.11.9 or higher. (Again,
[nvm](https://github.com/creationix/nvm) is a great tool for managing your
node version per project).

You can use the package.json's `scripts` property to wrap up the
start command for your app.

```json
//package.json
{
	...
	"scripts": {
		"start": "node --harmony server.js"
  }
	...
}
```

At this point you should be able to run your app with `npm start`.

##Big Gulps, huh?

I'm a big fan of gulp – it's a simple yet powerful task manager
for any javascript app. We're going to use it here to get some
live-reload going to speed up or development.

`npm i --save-dev gulp gulp-nodemon`, then throw this in a `gulpfile.js`

```javascript
//gulpfile.js
var gulp = require('gulp'),
  nodemon = require('gulp-nodemon');

gulp.task('nodemon', function() {
	nodemon({
		script: 'server.js',
		nodeArgs: ['--harmony']
  }).on('restart');
});

gulp.task('default', ['nodemon']);
```

You should now be able to run your app with `gulp`,
and changes to any file should restart the server without an issue.

##Start your testing engines

I'm also a big fan of TDD, or at the very least, testing early and often.
It's important to get your testing environment setup early in any project
– difficulty getting tests running is a terrible excuse for no tests.

We're going to use mocha, chai, and co-supertest to set up an endpoint-level
test.

`npm i --save-dev gulp-mocha-co gulp-exit chai co-supertest supertest`

```javascript
//test/endpoint.js
var app = require('../server.js');
var request = require('co-supertest').agent(app.listen());
var expect = require('chai').expect;

describe('/ endpoint', function() {
	it('should return Hello, World', function *(){
		var res = yield request.get('/').expect(200).end();
		expect(res.text).to.equal('Hello, World');
  });
});
```

There are a few things going on here:

- We're using co-supertest as a wrapper over the
[supertest](https://github.com/visionmedia/supertest) library to expect a 200
as the status code.
- We're using chai's expect library to test the response text
- Our test functions are actually generators! This lets us `yield`
async responses without needing a callback or promise structure - we'll
use gulp-mocha-co to implement that.

Let's get these tests failing! add this to your gulpfile:

```javascript
//gulpfile.js
var mocha = require('gulp-mocha-co'),
  exit = require('gulp-exit');

gulp.task('test-once', function(){
  gulp.src(['test/*.js'])
    .pipe(mocha({
    	reporter: 'nyan'
    }))
    .pipe(exit());
});
```

With all that in place, we should be good to go, right?
Try `gulp test-once` and see what happens.
That old `Unexpected token *` again, huh?

Because we're using generators in our tests, we need our good-ole
--harmony flag attached to our test command.
We'll update our package.json to solve this again.

```javascript
//package.json
{
	...
	"scripts": {
		"test": "node --harmony `which gulp` test-once"
  }
	...
}
```

Now you can run your tests with `npm start` - you should have one failing.
Let's get it to pass!

##Your Just-Approved Endpoint

Koa itself is very slim - we'll have to add some koa-* modules to get
routes and logging working.

`npm i --save koa-logger koa-route`

```javascript
//server.js
var logger = require('koa-logger'),
  route = require('koa-route');

...

app.use(logger());

app.use(route.get('/', function*() {
	this.body = "Hello, World";
}));
```

This should be enough to get your test passing! Run `npm test` to see it.

You should also see your app running in the browser - run `npm start`
and navigate to port 8000.

##Live Re-running tests

##A healthy refactor

server.js
api/endpoint.js
gulpfile update

##Lather, rinse, repeat.

a new endpoint

##Next steps:

  - perhaps a database?
  - plans for a front-end - connect to the Angular Gulp CRUD series
