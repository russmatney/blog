---
layout: post
title: 'Angular Jekyll and ngRepeat Headers'
categories:
- Techsposure
tags:
- jekyll
- angularjs
- ng-repeat
type: post
published: true
---

*TL;DR*:

AngularJS filtering in my Jekyll blog (as featured on [my homepage](/)) via this awesome [gist](https://gist.github.com/RainerAtSpirit/3076150), and I get into an Angular pattern I answered in [this Stack Overflow thread](http://stackoverflow.com/questions/17652665/angularjs-hide-parent-element-if-children-loop-is-empty-filtered).

***

I like to work when I travel,
so when I had a few hours between New York and Vegas this weekend,
I decided to implement something I've been thinking about for a long time:
AngularJS filtering in my happy little Jekyll blog.

I've been thinking about it for a while,
and googled it a few times.
This time, I found the most amazing little [gist](https://gist.github.com/RainerAtSpirit/3076150).
The simplifying key here is using Jekyll's [Liquid templating](http://liquidmarkup.org/)
to build the array of objects you want to filter.
After that, it's all [angular filtering magic](http://docs.angularjs.org/api/ng.filter:filter).

Angular filtering gives me full-text search of my blog,
which will make it easy to find whatever resource or story I'm looking for in no time.
The above gist also features a filter for some nice regex-powered highlighting.

***

While adding filtering to my blog,
a design pattern came up that I'd seen a few months back:
ng-repeats with headers. 
The problem is the same as in [this Stack Overflow thread](http://stackoverflow.com/questions/17652665/angularjs-hide-parent-element-if-children-loop-is-empty-filtered).
Specifically, I had posts in four sections of the blog that I was filtering simultaneously,
each with individual headers. 
At first, this builds out like so:

```html
<div ng-repeat="section in section">
	{% assign blerg = '{{section.name}}' %} {{blerg}}
	<div ng-repeat="post in section.posts | filter:searchText">
		{% assign blerg = '{{post.title}}' %} {{blerg}}
	</div>
</div>
```

And this works great!
Until your filter eliminates all of the posts in a section,
and you have a floating header with no posts. 
Not a huge deal, but not perfect either.

This turned out to be quite an annoying issue.
I couldn't find an easy way to get the inner ng-repeat to communicate to the outer that it was empty,
especially not on the fly,
short of building two seperate controllers or working some directive magic.

Looking into the [ng-repeat documentation](http://docs.angularjs.org/api/ng.directive:ngRepeat),
I found a few helper variables tied to the ng-repeat directive's `$scope`.
Apparently we can use `$first` to check if we're in the first element.
Which works out great for us.
Check this out:

```html
<div ng-repeat="section in section">
	<div ng-repeat="post in section.posts | filter:searchText">
		{% assign blerg = '{{section.name}}' %} <span ng-if="$first">{{blerg}}</span>
		{% assign blerg = '{{post.title}}' %} {{blerg}}
	</div>
</div>
```

We can move the section header into the inner ng-repeat,
and use angular's ng-if directive to build that element only when the first element exists.
This will remove the section header in the case that the inner ng-repeat is empty.
Angular is pretty slick, huh?

A quick note: at first I used `ng-show="$first"`, 
which works just fine.
However, ng-hide/show will still build the element every time, 
and then set it's `display: none`.
`ng-if` (available as of Angular 1.1.5, according to [this thread](http://stackoverflow.com/questions/16777152/angularjs-ng-if-boolean-condition-doesnt-work))
won't build those extra headers at all,
and lets you keep the DOM nice and clean.

A working example showing the before and after:

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.1.5/angular.min.js"></script>
<script type="text/javascript" src="../js/jekyll-search-controller.js"></script>

<div ng-app="JekyllDemoApp" ng-controller="JekyllDemoCtrl">
	<input type="text" style="margin:10px;float:left" ng-model="searchText" placeholder="type to filter...">
	<div style="margin:2%;width:43%;position:relative;float:left; clear:both">
		<div ng-repeat="section in sections">
			<h5 ng-bind-html-unsafe="section.name"></h5>
			<div ng-repeat="post in section.posts | filter:searchText">
				<span ng-bind-html-unsafe="post.title"></span>
			</div>
		</div>
	</div>

	<div style="margin:2%;width:43%;position:relative;float:right; clear:right">
		<div ng-repeat="section in sections">
			<div ng-repeat="post in section.posts | filter:searchText">
				<h5 ng-if="$first" ng-bind-html-unsafe="section.name"></h5>
				<span ng-bind-html-unsafe="post.title">d</span>
			</div>
		</div>
	</div>
</div>