---
title: ""Gettin' mobile-webby wit it (Native vs PhoneGap vs Titanium)""
date: 2013-06-21T00:00-04:00[America/New_York]
category: techsposure
tags: [Techsposure, tech]
---


Yes, that was a <a title="Gettin' Jiggy Wit it" href="http://www.dailymotion.com/video/x1hv4v_will-smith-gettin-jiggy_music#.Ub6S2PbD7rY">Gettin' Jiggy Wit It</a> reference.

Lately I've wandered from iOS land into the world of non-native app development, and I'm going to be in hot pursuit of performance boosting CSS techniques, handy-dandy HTML5 tricks, and browser-compatibility smoothing for the next few weeks. This post: two tiny but packed articles and then a <a title="PhoneGap" href="http://phonegap.com/">PhoneGap</a> Vs <a href="http://www.appcelerator.com/developers/">Titanium</a> Vs Native comparison. TL;DR: I'm prototyping with PhoneGap because it has the smallest learning curve, the widest reach, and because the mobile web is improving rapidly these days.

I found two great (and very concise) articles on HTML5 from <a title="Adnane Belmadiaf twitter" href="https://twitter.com/AdnaneBelmadiaf/">Adnane Belmadiaf</a> last week, one with <a title="5 html5 need to know features" href="http://daker.me/2013/05/5-html5-features-you-need-to-know.html">5 need-to-know HTML5 features</a>, one with <a title="5 html5 javascript apis to keep an eye on" href="http://daker.me/2013/06/5-html5-javascript-apis-to-keep-an-eye-on.html">5 awesome HTML5 + Javascript mobile APIs</a>. The APIs are pretty mobile-awesome, check them out.

I mentioned <a title="Staying UX to date" href="http://russellmatney.wordpress.com/2013/06/16/staying-ux-to-date/">a few days ago</a> that I found a <a title="AngularJS Phonegap" href="http://briantford.com/blog/angular-phonegap.html">great PhoneGap + AngularJS tutorial</a>. This was after I spent a few days making the classic <a title="PhoneGap" href="http://phonegap.com/">PhoneGap</a> Vs <a title="Appcelerator Titanium" href="http://www.appcelerator.com/developers/">Appcelerator Titanium</a> decision. I have always been a huge Native-is-just-always-better advocate, without having done much research into any hybrid or otherwise non-native tech. However, the draw to build once and deploy everywhere is a strong business and dev-speed motivator, and a little research has pushed me into the prototyping stage. Below is my take per platform so far, according to experience plus some readings from <a title="StackOverflow - Phonegap, titanium, corona" href="http://stackoverflow.com/questions/1482586/comparison-between-corona-phonegap-titanium">StackOverflow</a>, a <a title="Andrey butov goes native" href="http://www.andreybutov.com/2012/08/25/why-i-choose-native-code-over-phonegap-or-appcelerator-titanium/">quick post from a guy who went native over non-native</a>, and a <a title="Titanium vs phonegap a fair comparison" href="http://spider21.wordpress.com/2013/02/02/titanium-vs-phonegap-a-fair-comparison/">BIG post from a Titanium employee who breaks it all down in a very fair and very informative way</a>.

<strong><a title="PhoneGap" href="http://phonegap.com/">PhoneGap</a></strong> gives web developers a chance to build an app for every device with a browser one time, using html, css, and javascript (assuming you don't need any true native functionality). It runs a 'native' app that fires up the device's native browser to run a web app, and connects to device features via the <a title="PhoneGap Docs" href="http://docs.phonegap.com/en/2.8.0/index.html">PhoneGap API</a>.

Advantages:
<ul>
	<li>Developers can build and test the app in their own browser</li>
	<li>You don't need specialized devs for each language/device (at least not for the entire app)</li>
	<li>Devs can write their own plugins to hybridize (mixing with true native parts of the app)</li>
	<li>The app can be hosted elsewhere online, giving you magical update-everywhere-anytime powers</li>
	<li>PhoneGap can hit, what, 7 different OSs? Apple, Android, Windows, Blackberry, etc.</li>
</ul>
Disadvantages:
<ul>
	<li>Web apps might not "feel" native, leading to a potentially poorer user experience (although this is getting better as mobile browsers improve and developers get smarter about performance)</li>
	<li>Browser-compatibility becomes an issue (although you'd have fragmentation headaches going native anyway)</li>
	<li>Devs have to learn to work with PhoneGap (there's always a learning curve)</li>
</ul>
<a title="Appcelerator titanium" href="http://www.appcelerator.com/developers/"><strong>Titanium</strong> from Appcelerator</a> comes with it's own IDE and can be written entirely in Javascript (again, assuming you don't require true native hybridization). It focuses much more sharply on iOS and Android, allowing its API to dig a little deeper into those OSs' core functionalities.

Advantages:
<ul>
	<li>A closer-to-native feel that can be written in Javascript (you are basically working with JS-ized native elements)</li>
	<li>Devs can build their own native "modules" to interact with (similar, I think, to PhoneGap's plug-ins)</li>
	<li>Potentially faster-than-native development speed (build once, deploy to iOS and Android)</li>
</ul>
Disadvantages:
<ul>
	<li>Higher learning curve (new IDE, more in-depth API, and no html/css control - you are essentially building a native app with Javascript)</li>
	<li>Only supports iOS and Android at the moment</li>
	<li>Devs can't test in the browser the way they could with a web app (although PhoneGap apps should really be tested on the device/in simulators anyway)</li>
</ul>
On native vs non-native apps: While non-native apps can save 60-90% of code, there is something to be said for developing a fully native app on one device (say, iOS) then asking a (say, Android) developer to build a replica app from scratch. It's a great project, and I've heard tell of some success stories (and I should add some links here but am tired and will hope you trust me). It can also prevent one multi-OS-assigned developer from the headaches that a single-OS specialist would fly through. Also, native apps can provide the best user experience, although that will really depend on the team building the app.

Fully native apps, however, are not always accessible to the whole development team (unless you are REALLY stacked), and keeping content, style, and updates consistent can become a project management nightmare.

My thinking at the moment: if PhoneGap has the fastest development speed and modern browsers are rapidly improving, why not take that chance? If the UX is comparable and we can avoid the unholy Apple App Store update process with some dynamic content, then I'm sold. And if there's anything that the mobile web can't handle, we'll just link to a native view for that piece of complexity.

I'm prototyping now. The new fear: if my app sucks, is that my fault or PhoneGap's?
