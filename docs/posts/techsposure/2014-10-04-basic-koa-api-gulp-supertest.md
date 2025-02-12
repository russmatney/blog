---
layout: post
title: 'Basic Koa API + Gulp + Supertest'
categories:
- Techsposure
tags:
- Koa
- Nodejs
- Gulp
type: post
---

###*TL;DR*:

A step-by-step walkthrough for building a Koa app, setting up a gulp dev
environment, and getting a few endpoint-level tests covering your CRUD
from the beginning.

All code in this post can be found on [github](https://github.com/russmatney/koa-gulp-crud).
*Note: Some code was modified during the writing of the post that has not yet been
updated in the linked repo. Update to come soon.*

---

##A bit on generators

JavaScript has a few new things coming in ECMA 6, one of which is
a new kind of function called a generator.
I hope to get into the details of how generators work in another post,
but for now, you can recognize a generator by the `*` added to the
function signature, and the use of a new keyword `yield`:

    var doWork = function *() {
      console.log("I am a generator");
      var data = yield getData();
      console.log(data);
    }

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

    //server.js
    var koa = require('koa');

    var app = module.exports = koa(),
      port = process.env.PORT || 8000,
      env = process.env.NODE_ENV || 'development';

    app.listen(port);
    console.log('app listening on port: ', port);

If we try to run this with `node server.js`,
you should see an error like `SyntaxError: Unexpected token *`.
`node --harmony server.js` should run smoothly (displaying the console.log
above). If you're still seeing the same syntax error,
make sure you're running node v0.11.9 or higher. (Again,
[nvm](https://github.com/creationix/nvm) is a great tool for managing your
node version per project).

You can use the package.json's `scripts` property to wrap up the
start command for your app.

    //package.json
    "scripts": {
      "start": "node --harmony server.js"
    }

At this point you should be able to run your app with `npm start`.

##Big Gulps, huh?

I'm a big fan of gulp – it's a simple yet powerful task manager
for any javascript app. We're going to use it here to get some
live-reload going to speed up or development.

`npm i --save-dev gulp gulp-nodemon`, then throw this in a `gulpfile.js`

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

You should now be able to run your app with `gulp`,
and changes to any file should restart the server without an issue.

##Start your testing engines

I'm also a big fan of TDD, or at the very least, testing early and often.
It's important to get your testing environment setup early in any project
– difficulty getting tests running is a terrible excuse for no tests.

We're going to use mocha, chai, and co-supertest to set up an endpoint-level
test.

`npm i --save-dev gulp-mocha-co gulp-exit chai co-supertest supertest`

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

There are a few things going on here:

- We're using co-supertest as a wrapper over the
[supertest](https://github.com/visionmedia/supertest) library to expect a 200
as the status code.
- We're using chai's expect library to test the response text
- Our test functions are actually generators! This lets us `yield`
async responses without needing a callback or promise structure - we'll
use gulp-mocha-co to implement that.

Let's get these tests failing! add this to your gulpfile:

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

With all that in place, we should be good to go, right?
Try `gulp test-once` and see what happens.
That old `Unexpected token *` again, huh?

Because we're using generators in our tests, we need our good-ole
--harmony flag attached to our test command.
We'll update our package.json to solve this again.

    //package.json
    "scripts": {
      "test": "node --harmony `which gulp` test-once"
    }

Now you can run your tests with `npm start` - you should have one failing.
Let's get it to pass!

##Your Just-Approved Endpoint

Koa itself is very slim - we'll have to add some koa modules to get
routes and logging working.

`npm i --save koa-logger koa-route`

    //server.js
    var logger = require('koa-logger'),
      route = require('koa-route');
    ...
    app.use(logger());

    app.use(route.get('/', function*() {
      this.body = "Hello, World";
    }));

This should be enough to get your test passing! Run `npm test` to see it.

You should also see your app running in the browser, and routes logged wherever
your server is running - run `npm start` and navigate to port 8000.

##Live Re-running tests

So far, here's what we've got:

- Koa server with routes and logging
- Tests exercising one endpoint
- Live-reload of code on file save

The next thing to add is live-reload for our tests.
Whenever I save a file, I want to see what tests are passing or failing
as soon as possible - the shorter that turn-around time is, the faster you'll
be able to improve your code.

We're going to add a `watch` task to our gulpfile that will react to changes
to specific files. We're also going to refactor our test-once function to run
the our simpler mocha task. `test-once` will exit after tests are run (good for
CI purposes), while `mocha` will run repeatedly.

    //gulpfile.js
    gulp.task('watch', function() {
	    gulp.watch(
	      ['*.js', 'test/*.js'], //blurbs of files to watch
	      ['mocha'] //tasks to run when the above files change
      );
    });

    gulp.task('mocha', function() {
      return gulp.src(['test/*.js'])
        .pipe(mocha({
          reporter: 'nyan'
        }));
    });

    gulp.task('test-once', function() {
      gulp.tasks.mocha.fn().pipe(exit());
    });

    gulp.task('default', ['nodemon', 'mocha', 'watch']);

Normally this would be enough – a simple `gulp` will start your server,
run your tests, and restart both of those things on any file change.
But we're on the cutting edge of javascript here - and our tests are going
to whine if our process doesn't flag `--harmony`. We can fix that the same
way as before - via the package.json.

Everything should run fine with the 'node --harmony `which gulp`' command,
so that's what we'll drop into our package.json:

    //package.json
    "scripts": {
      "start": "node --harmony `which gulp`"
    }

Now we can do it all with `npm start`.

##EADDRINUSE

Except what's this error? E-ADDR-IN-USE?

EADDRINUSE pops up whenever a process wants to run on a PORT that's
already exposing a process. In our case, our app is running on port 8000
via Nodemon, and then supertest tries to run it again in our mocha tests.

One way to solve this problem is to specify a different PORT based on the
environment. That's probably the easiest solution at this point.

In our gulpfile, add a PORT to both the `nodemon` task and the `mocha` task.

    gulp.task('nodemon', function() {
      nodemon({
        script: 'server.js',
        env: {PORT: 8000},
        nodeArgs: ['--harmony']
      }).on('restart');
    })

    gulp.task('mocha', function {
      process.env.PORT = 8001;
      return gulp.src([...])
        ....etc
    });

That ought to do it. `npm start` should start your server and run your tests,
and any save to js file should restart and re-run both.

##A healthy refactor

Now we're in business. The rest of our work can be done while `npm start` is
running, with blissful instant feedback bliss.

We are good developers! We write maintainable code! Let's refactor our endpoint
functions out of the server file.

    //server.js
    var endpoint = require('api/endpoint');
    app.use(route.get('/', endpoint.show);

Create a new directory and file for our endpoint:

    //api/endpoint.js
    module.exports.show = function*() {
      this.body = "Hello, World";
    }

Bada-bing, bada-boom, this code is refactored, and we know it's still working
great because our tests are passing.

##Lather, rinse, repeat.

The last thing we'll do for now is add a new endpoint, starting of course with a
test for it.

    //test/endpoint.js

    it('should create an object', function *() {
      var object = {ziggity: 'zap'};
      var res = yield request.post('/').send(object).expect(201).end();
      expect(res.body.created_at).to.exist;
      expect(res.body.ziggity).to.equal('zap');
    });

Once your test fails, we can add the route and handler to our Koa app.
Run `npm i --save co-parse` to handle the request body with ease.

    //server.js
    app.use(route.post('/', endpoint.create));

    //api/endpoint.js
    var parse = require('co-body');

    module.exports.create = function*() {
      var object = yield parse(this);
      object.created_at = new Date;
      // TODO: save to DB
      this.status = 201;
      this.body = object;
    }

We aren't saving to the DB yet, but we are enforcing some parts of our
interaction, such as setting the status and created_at date, as well as
returning our 'created' object to the client.

##Next Steps

You're off and running with a functional dev-env for building whatever Koa app
you'd like. Congrats! In a future post, I'd love to get this app talking with an
actual database (mongo or rethinkdb perhaps?). If you want to start in on that
now, check out some of the other [Koa
examples](https://github.com/koajs/examples).

Reach out to me in the comments below or on [Twitter](https://twitter.com/russmatney)
if you've got any questions.
