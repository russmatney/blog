---
title: ""'Swift is sweet - Workflow and tools that pleasantly surprised me'""
date: 2015-04-18T00:00-04:00[America/New_York]
category: techsposure
tags: [Techsposure, tech]
---



###*TL;DR*:

Vim in Xcode via [XVim](https://github.com/XVimProject/XVim)
is crucial.

[Alactraz](http://alcatraz.io/)
is the essential Xcode plugin manager.

Some sweet libs you might like:

- [Bond](https://github.com/SwiftBond/Bond)
is an interesting data-binding framework for Swift.
- [Alamofire](https://github.com/Alamofire/Alamofire)
is an awesome abstraction for all your HTTP.
- [SwiftyJSON](https://github.com/SwiftyJSON/SwiftyJSON)
because of course you're using JSON.

[NSHipster](http://nshipster.com/),
[objc.io](http://www.objc.io/),
[RayWenderlich](http://www.raywenderlich.com/)
are my most-used iOS learning resources.

---

Have you swifted lately?
Swift 1.2 is officially out, which is good news:
compiling is faster and more consistent,
and we all know Xcode could crash less often.

I recently got back to building apps in iOS after a few years in browser-land,
and was pleasantly surprised to find a number of tools that made my life easy.

This post is a quick look at a few of them.

##Own your workflow!

Workflow is HUGE.
Minimizing the cognitivie overhead of development has a direct impact on the quality of your work.
Never settle for good-enough in your workflow
- you'll thank yourself for improvements immediately.

###XVim

I'm a Vim addict, and getting back into iOS without it would be a tragedy.
Luckily, I don't have to!

[XVim](https://github.com/XVimProject/XVim)
is an awesome, well-maintained plugin
that gives you Vim super-powers in Xcode.

You can even write your own .xvimrc!
[Mine is here](https://github.com/russmatney/dotfiles/blob/master/xvimrc).

###Alcatraz

[Alcatraz](http://alcatraz.io/)
is an awesome plug-in manager for Xcode.
It'll change your life!

Most plugins are fixes for things that should really be in Xcode already.

Some plugins that I like:

- [FuzzyAutocomplete](https://github.com/FuzzyAutocomplete/FuzzyAutocompletePlugin) - because duh.
- [See the whole issue in the navigator](https://github.com/neonichu/BBUFullIssueNavigator) - should really be fixed in Xcode.
- [Are you sure? Before Quitting](https://github.com/StefanLage/XQuit/) - because I quit in a hurry sometimes.
- [Adjust the font with cmd +/-](https://github.com/zats/AdjustFontSize-Xcode-Plugin) - Like every other Apple app.

##Don't reinvent all the wheels

[CocoaPods](https://cocoapods.org/)
is a convenient package manager –
and there are definitely useful open-source packages for iOS apps.

###Bond, Swift Bond

[Bond](https://github.com/SwiftBond/Bond)
is a nice way to bind some data in your app to some fields in your app's interface.
It can dry up quite a bit of code in your view controllers,
which means really good things for maintainability.

###Alamofire

The successor to AFNetworking,
[Alamofire](https://github.com/Alamofire/Alamofire)
is a bad-ass little networking lib that will make your http usage easy-peasy.

    Alamofire.request(.GET, "http://httpbin.org/get", parameters: ["foo": "bar"])
      .response { (request, response, data, error) in
                  println(request)
                  println(response)
                  println(error)
                }

###SwiftyJSON

Swift has great type safety; however,
one side-effect of its implementation makes for nasty JSON unwrapping.

Enter [SwiftyJSON](https://github.com/SwiftyJSON/SwiftyJSON).

##Resources

It's a great time to be an iOS dev!
Xcode isn't as bad as I remember it,
and Swift is a really nice language to work with.

For more info, my favorite iOS dev related resources:

- [NSHipster](http://nshipster.com/)
- [objc.io](http://www.objc.io/)
- [RayWenderlich](http://www.raywenderlich.com/)


