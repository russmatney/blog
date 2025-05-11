# Boston Godot Con

> NOTE: I tried to be comprehensive for my own experience of the conference -
> there were lots of talks and showcases that I missed that are unfortunately
> not represented in this post.
>
> You can find the [full schedule
> here](https://conference.godotengine.org/2025/schedule/), and when the
> conference talks hit YouTube, I'll update this to include a link to that
> playlist.

I'm feeling crazed but ambitious and in good spirits after a very busy few
weeks. I got married! Then did a weekend in the catskills with a bunch of
hyperactive musicians! Then went to [Godot Con
Boston](https://conference.godotengine.org/2025/)! What?! Yeah!

I finally got back Thursday and have been reeling from the energy. I've been in
one of those ambitious, over-committing moods - writing out TODOs for all my
projects, and trying really hard not to commit to any of them. Wish me luck!

TODO screenshot massive todos list

Godot Con was a blast! So many cool godot nerds - developers, artists,
musicians, indie game makers all around. There were awesome games at the
showcase.

I was staring wide-eyed at someone else playing [Burrito
Bear](https://store.steampowered.com/app/1941420/Burrito_Bear/) game for a full
minute, trying to take it all in. Space Orca turned and asked if I wanted to
play. Hell yeah I do!

I'm side-stepping around, eating burritos and batting away trash with a gorgeous
island sunset in the background, when Space Orca hits some secret dev-ghost
keybindings. Suddenly, the sky is filled with `BSP`s. I catch one, and we
enter a hand-held pixel art minigame - I'm excite-biking the bear on a
motorcycle over jumps with o.g. wario-ware vibes.

TODO insert screenshot.

This conference was about to be incredible!

--

My favorite talk from Day 1 (and maybe the whole conference) was badcop's
mixed-reality streaming hacks. It was an awesome hack-tale full of ingenuity,
rabbit-hole-avoiding-cut-corners, and some awesome visual results. One of my
TODOs is to hop in that discord and hack an OSX or Linux version of that
together.

There was solid Godot OSX content on day 1 - [Xogot](https://xogot.com/) (the
Godot Editor as a native iPad app) and the
[`SwiftGodotKit`](https://github.com/migueldeicaza/SwiftGodotKit) that supported
it. I'll definitely be exploring this more for my DotHop iOS port.

Jeff Ward's overview of impling your own scripting language for Godot is still
hitting me. My brain has been rooting around, wondering if it's crazy to try to
support a clojure-like syntax as a scripting language for Godot. (I hacked some
.tscn <> .edn thing together a few years ago called
[godot-edn](https://github.com/russmatney/godot-edn), but it there's not much
use for it just yet.

- [Dukkido](https://store.steampowered.com/app/3186280/Dukkido/) by
  [MariSuCho](https://store.steampowered.com/developer/MariSuCho)

Eric Peterson's ([Baja The Frog]()) Event-Bus talk was enlightening. I'd seen a
bit about this pattern before, but always shyed away - I assumed I'd need to
implement some fancy library to make it reasonable. Nope! Just an autoload with
some signals on it! I'm hopeful to delete a bunch of defensive code in Dino :D
[Here's a blog post covering
it](https://dev.to/bajathefrog/riding-the-event-bus-in-godot-ped).

Rawb Herb's UI design talk was also awesome, and so was Adam Scott's state of
the Godot on the Web. Plus Casey + Sarah's Cross-Disciplinary Collaboration
tips (and love for custom resources and in-house tooling). HP van Braam's
nanotech tool was mind-blowing. So many insights and so much inspiration to
glean!

By the end I was overwhelmed and socially fried, and walked back to the hotel in
the rain, which I rather enjoyed.

--

Early in Day Two,
[Dunderbeck](https://store.steampowered.com/app/2477750/Dunderbeck/) showed me
that one way to make sausage is via an inventory-management autobattler with a
penchant for rats. So there's that.

I met [Josh](https://joshuagalecki.itch.io/), part of [Tiny Mass
Games](https://tinymassgames.itch.io/), a community of game devs drawing
inspiration from [Sokpop](https://www.sokpop.co/). They align their schedules to
release solo and small-team games every 2 months, then take a month off. What a
great cadence! I'm hopeful to join for Season 11, starting in July!

Chad Stewart's ([binarysolo](https://www.binarysolo.com/)) talk showed some
great mobile game-dev tools, if you lose your laptop to a flood but still need
to make an indie game. Between this and Xogot, I'm excited to get more time
working on games away from the (proper) keyboard.

Acerola's dry sense of humor was a riot, and now I know that a CE (some shader
term) is a generic work layer at the bottom of a GPU's well... or something like
that.

> Digging through my notes, it's a `Compositor Effect` - [here's a tutorial for
> understanding them
> better](https://docs.godotengine.org/en/stable/tutorials/rendering/compositor.html).
> I can't read this without laughing aloud at how lost I feel!
> Maybe this is a good candidate for a future stream or video tutorial.

The Godot AMA was great - too much to capture here. One moment I liked was
Emi's 5-years-in-the-future dream: more tools supporting all the needs in the
ecosystem. Godot is not trying to grab all the game-devs or support every
game-dev's need. The recent rise in popularity has led to problems of scale.
It is difficulty to support users with a wide variety of needs -
some want amazing, Unreal-Engine-like 3D, others just want an upgraded
RPG-Maker, etc. More tools to explore? Defold, Bevy, Haxe, Luxe, all come to
mind - maybe it's worth exploring a bit.

Chris Ridenour's Multiplayer Dome Keeper talk was spot on, and I'd have liked it more were
it not for the multiplayer-impl-flashbacks I was having from [Rapid Eye Madness](https://store.steampowered.com/app/3248030/Rapid_Eye_Madness/)'s
build last fall. (I guess it's still too soon, haha.) The talk did a great job of
breaking down all the multiplayer decisions you need to make to bring your game
to more than one controller.

StayAtHomeDev's talk may have been my favorite overall - the strong throughline
was encouragement to be yourself and to make things that are YOU. He also
outlined a reasonable approach to finding your voice as a Youtuber - it's
inspired me to create a few more videos without sweating the stats. Maybe a
T-shirt Tier List, or something fun?

--

A fun occurrence at Godot Con was running into other New York City folks. We
were generally inspired (jealous?) of the Boston Godot User Group's traction,
and have since started our own Discord, laying plans to meet up and talk Godot
in the Big Apple. I'm pumped to be a part of this new group! Reach out if you'd
like to join!

--

Finally, there was nothing left by the Lightning Talks, of which I was the first
to go. After battling nerves the whole conference, they reached a head in the
last half-hour before my sentencing.

A major thank you to the other Lightning Talk speakers, who commisserated about
our nerves beforehand. Maaak especially helped when he recommended a deep
breath and a power pose before starting.

The talk went by in a blur - the experience of it was very: "why am I out of
breath?", "what words am I saying?". At this poing I think it went pretty well
overall, and I'm excited to watch the recording. I got great feedback and
several people told me they're excited to use
[Log.gd](https://russmatney.github.io/log.gd/#/) in their own projects.

> I'm working to beef up the documentation and clean up some of the
> code/features before the talk hits youtube (and after ofc.)
>
> Feel free to reach out if you have any issues or ideas!

--

And now, things are settling back down. No more Godot talks to go to, just a
hundred steam games, itch projects, github urls, and blog posts to wade through.

Thank you x 1,000,000 to the volunteers, coordinators, organizers, speakers,
attendees, etc. The conference was a major success, and I mean to capitalize on
all the energy I got out of it. Thank you all, and hope to see you again soon!

## Games and Folks

Show case games I was able to play for bit:

- [Burrito Bear](https://store.steampowered.com/app/1941420/Burrito_Bear/) by
  [Space Orca](https://store.steampowered.com/curator/42124765)
- [Dunderbeck](https://store.steampowered.com/app/2477750/Dunderbeck/) by [Rust
  LTD](https://rustltd.com/)

Other games shared by folks I met:

- [Phosfi](https://store.steampowered.com/app/2625450/Phosfi/) by Ben at UpRoom
  Games
- Several [Splashy Inc](https://splashy-inc.itch.io/) Games, a number of which
  run on mobile!
