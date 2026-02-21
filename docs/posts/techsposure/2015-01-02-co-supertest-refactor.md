---
title: ""'Glorious E2E refactor via generators and co'""
date: 2015-01-02T00:00-05:00[America/New_York]
category: techsposure
tags: [Techsposure, tech]
---



###*TL;DR*:

I just refactored some E2E tests from a supertest implementation to one that uses
generators via [`co-supertest`](https://github.com/avbel/co-supertest)
(basically [`supertest`](https://github.com/tj/supertest)
wrapped with [TJ's co lib](https://github.com/tj/co))
for massive readability and therefore maintainability improvements.

---

##E2E testing is important and very useful, but can get messy

I love using e2e tests to get a project up and running.
Once a few CRUD tests are in place, I'm free to play with whatever
frameworks and architectures I want
without fear of breaking any client-server contracts.

I recently built out a small API with ExpressJS,
and included some E2E protection via `supertest`,
a lib that makes testing HTTP request/response easy.

Basic E2E test with `supertest`:

    it('should return a 200 and data on a get to /users', function(done) {
      request.get('/users')
        .expect(200)
        .expect(function(res) {
          if (!res.body.users) throw new Error('No users array or object on response body');
          if (res.body.users.length !== 0) throw new Error('Array should be empty');
        })
        .end(function(err, res){
          if (err)
            done(err);
          else
            done();
        });
    });

The above example is fine.
But, when you get to a slightly more complex use-case,
the code quickly resembles the Pyramid of Doom.
One example: Authentication e2e tests.

Short of fleshing that example out and for the sake of the length of this post,
here's the co-supertest implementation of the above example. Feast your eyes!

    it('should return a 200 and data on a get to /users', function*() {
      var res = yield request.get('/users')
        .expect(200)
        .end();

      if (!res.body.users)
        throw new Error('No users array or object on response body');
      if (res.body.users.length !== 0)
        throw new Error('Array should be empty');
    });

Much more pleasing to the eye.
The use of `yield` lets us keep our functions a minimal number of layers deep,
and eradicates ugly scoping issues that might crop up.

Testing does not have to be painful! Own your code base!

If you're looking for any info on the implmentation of these tests,
check out my [Koa + Gulp + Supertest post](http://russmatney.com/techsposure/2014/10/04/basic-koa-api-gulp-supertest/)

Let me know if this post could use some more in-depth examples,
I'm happy to elaborate more! Reach out here or via Twitter (@russmatney).

