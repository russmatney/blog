---
layout: post
title: The best way to play NES games, and game dev resources
categories:
- Techsposure
tags:
- AI
- artificial intelligence
- box2d
- chipmunk
- cocos2d
- enchantjs
- gaming
- impactjs
- ios
- karate kid
- mario
- minecraft
- nes
- pac-man
- ray wenderlich
- super mario
- technology
- tetris
- tom murphy
- tom7
- unity3d
- wired
status: publish
type: post
published: true
meta:
  _publicize_pending: '1'
  _wpas_done_1917337: '1'
  _publicize_done_external: a:1:{s:8:"facebook";a:1:{i:569696174;b:1;}}
  publicize_twitter_user: RussMatney
  _wpas_done_1917358: '1'
  _wpas_done_1917361: '1'
---
I saw a <a title="WIred AI Mario" href="http://www.wired.co.uk/news/archive/2013-04/12/super-mario-solved">great article in Wired</a> this weekend about <a title="Tom7" href="https://twitter.com/tom7">Tom Murphy</a>, a software engineer who built a generic program to play NES games. He built two tools, one to record himself playing his NES, and one to play it on its own. It does pretty well! It finishes a few levels!

The amazing thing is how simple the program is, and how it applies to multiple games (except Tetris, as you'll see). The program doesn't use any pathfinding or advanced algorithms. It maps input from the controller and a relative screen position for the character, and then uses a few constraints to try to increase the player's score. <a title="Tom7 video" href="http://www.youtube.com/watch?feature=player_embedded&amp;v=xOCurBYI_gY">Watch the video</a> to see it trying to beat Super Mario, Karate Kid, Pac-man, and Tetris.

The article mentions that there are other competitions for Mario-playing algorithms. <a title="Crazy mario AI" href="http://www.youtube.com/watch?v=u_0pgFQ8HcM&amp;feature=youtu.be">Check out this super smart Mario AI in a chaotic custom made level</a>.

I've dabbled in game development, and if you're interested, there are some great resources. On iOS, <a title="Ray Wenderlich tutorials" href="http://www.raywenderlich.com/tutorials">Ray Wenderlich</a> is still king, with tutorials on beginning, intermediate, and advanced game programming (not to mention everything else iOS). Ray's site has tutorials for getting into <a title="Cocos2d" href="http://www.cocos2d-iphone.org/">Cocos2d</a>, which can give you plenty to work with. If you want to add physics, <a title="Chipmunk" href="http://chipmunk-physics.net/">Chipmunk</a> and <a title="Box2d" href="http://box2d.org/">Box2d</a> are great options. If you want an even bigger jumpstart, King Wenderlich just put out a great set of <a title="Ray Wenderlich starter kit" href="http://www.raywenderlich.com/36854/platformer-and-beat-em-up-game-starter-kits-now-available-and-giveaway">starter kits</a>.

If you're iOS-shy, there are tons of JavaScript gaming frameworks. I've been toying with ImpactJS for a while and enjoying it. It's not free, but it is simple and includes a level editor.  There are other options too, for sure. Maybe <a title="Enchant JS" href="http://www.raywenderlich.com/23370/how-to-make-a-simple-html5-game-with-enchant-js">Enchant.js</a>? When you're ready for prime time, maybe something like <a title="Unity 3d" href="http://unity3d.com/">Unity3D</a>?

Of course, you could just keep playing <a title="MineCraft WIkipedia" href="http://en.wikipedia.org/wiki/Minecraft#Creative_mode">MineCraft</a>.
