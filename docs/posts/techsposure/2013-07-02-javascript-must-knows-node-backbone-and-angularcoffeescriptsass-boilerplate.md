---
layout: post
title: 'JavaScript must-knows: Node, Backbone, and Angular/CoffeeScript/Sass Boilerplate'
categories:
- Techsposure
tags:
- angular
- backbone
- bower
- coffeescript
- compass
- evan hahn
- express
- grunt
- javascript
- node
- npm
- sass
- yeoman
status: publish
type: post
published: true
meta:
  _wpas_mess: 'JavaScript must-knows: Node, Backbone, and Angular/CoffeeScript/Sass
    Boilerplate http://wp.me/p2ODBY-30'
  _publicize_pending: '1'
  _wpas_done_3911020: '1'
  _publicize_done_external: a:1:{s:8:"facebook";a:1:{i:569696174;b:1;}}
  publicize_twitter_user: RussMatney
  _wpas_done_1917358: '1'
  _wpas_done_1917361: '1'
---
JavaScript resources this post, touching Node/Express, Backbone, and Angular. Come on down!

<a title="Evan Hahn" href="http://evanhahn.com/">Evan Hahn</a> wrote <a title="Evan Hahn Understanding Express Js" href="http://evanhahn.com/understanding-express-js/">a great Express.js overview</a> that I've needed for a while now. <a title="Nodejs" href="http://nodejs.org/">NodeJS</a> is a platform for building network applications (i.e. non-static websites) that has become a JavaScript standard. Hahn's article covers just what a basic Node app is, what <a title="Connect" href="http://www.senchalabs.org/connect/">Connect</a> attempts to cover/simplify in Node, and then what <a title="Expressjs" href="http://expressjs.com/">Express</a> attempts to cover/simplify in Connect. It's well-structured and readable and I recommend it to everyone. It even gets into some basic <a title="Jade" href="http://jade-lang.com/">Jade</a> for HTML templating, which is a great thing to be familiar with.

I also found a great set of <a title="BackboneJS" href="http://backbonejs.org/">BackboneJS</a> tutorials. Backbone is a popular JavaScript framework that can be used for building web apps of any scale. A basic <a title="BackboneJS wine cellar tutorial" href="http://coenraets.org/blog/2011/12/backbone-js-wine-cellar-tutorial-part-1-getting-started/">3-part tutorial starts here</a>, and would be great if you want to see the way Backbone apps are structured, i.e. what all these Views, Models, Collections, Routers, etc. are for. If you want to get into the more server-side node stuff (with <a title="node-Restify" href="http://mcavage.github.io/node-restify/">Restify</a> this time) and <a title="Mongodb" href="http://www.mongodb.org/">MongoDB</a> (with Mongoose), <a title="Backbone tutorial: Node restify mongo mongoose" href="http://backbonetutorials.com/nodejs-restify-mongodb-mongoose/">dive right in here</a>.

Backbone is a great framework, but lately I highly recommend <a title="Angular JS" href="http://angularjs.org/">AngularJS</a>. What's the difference between frameworks, you ask? As I see it, the framework you choose determines how much "magic" you get. Different frameworks will do different things for you right out of the box (without you having to do anything). AngularJS, for example, does some  beautiful data-binding and live updating that would take a ton of work to achieve in other frameworks. Lots of "magic" can be very helpful, but can also lead to tricky debugging and distancing yourself from pure JavaScript. It really comes down to your personal language and architecture preferences.

That being said, I love <a title="CoffeeScript" href="http://coffeescript.org/">CoffeeScript</a> + AngularJS + <a title="Sass" href="http://sass-lang.com/">Sass</a>/<a title="Compass" href="http://compass-style.org/">Compass</a> these days. I've listed below the terminal sequence I run through to get an Angular/CoffeeScript/Sass boilerplate stack up and running in just a minute or two. It's all done via <a title="Yeoman" href="http://yeoman.io/">Yeoman</a>, <a title="GruntJs" href="http://gruntjs.com/">Grunt</a>, and <a title="Bower" href="http://bower.io/">Bower</a>, and you'll need to use <a title="NPMJS" href="https://npmjs.org/">npm</a> (Node Package Manager, super helpful, you should be using it if you aren't already). It even includes Karma for unit testing. Execute these commands inside the directory you'd like to add the app to.

{% highlight bash %}
npm install yo grunt-cli bower
npm install generator-angular
yo angular --coffee
// `Y` for yes to bootstrap, scss, and all the angular stuff.
npm install
bower install
grunt server
{% endhighlight %}

If `grunt` isn't found, try `npm install grunt` and then `grunt server` again. To get unit tests running (via `grunt karma`), you need to change `bower_components` in karma.conf.js to `components`. That should work, and if it doesn't, let me know. I'm happy to troubleshoot with you via comments here or <a title="Twitter Russell Matney" href="https://twitter.com/russmatney">twitter</a>.

Happy projamming!
