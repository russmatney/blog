> This post tries to be comprehensive for my own experience of the conference -
> there were so many other talks and showcases that I missed that are unfortunately
> not represented in this post. :cry:
>
> You can find the [full schedule
> here](https://conference.godotengine.org/2025/schedule/), and when the
> conference talks hit YouTube, I'll update this to include a link to that
> playlist.
> If there are other lists of all-the-things, lmk - I'd love to
> include a link to that.
>
> As the YouTube videos land, I'll update this post with some in-line
> links to make this more useful.

> Update: there's an [Interview with every Showcase
> Developer](https://www.youtube.com/watch?v=9NJn7dTTZnw) on Youtube - a great
> way to see all the indie games in the showcase!

# Boston Godot Con

I'm feeling crazed but ambitious and in good spirits after a very busy few
weeks. I got married! Then did a weekend in the Catskills with a bunch of
Hyperactive (self-proclaimed) musicians! Then went to [Godot Con
Boston](https://conference.godotengine.org/2025/)! What?! Yeah!

I finally got back Thursday and have been reeling from the positive energy. I've
been in one of those ambitious, over-committing moods - writing out TODOs for
all my projects, and trying really hard not to commit to any of them. Wish me
luck!

Godot Con was a blast! So many nice Godot nerds - developers, artists,
musicians, indie game makers all around. There were awesome talks, awesome games
at the showcase, and awesome projects hidden up the sleeve of everyone there.

## A Few Showcase Games

A few minutes after arriving Tuesday morning, I was captivated as someone else
played [Burrito Bear](https://store.steampowered.com/app/1941420/Burrito_Bear/).
Space Orca turned and asked if I wanted to play. Hell yeah I do!

I'm side-stepping around, eating burritos and batting away trash with a gorgeous
island sunset in the background, when Space Orca hits some secret dev-ghost
keybindings. Suddenly, the sky is filled with `BSP`s. I catch one, and we
enter a hand-held pixel art minigame - I'm excite-biking the bear on a
motorcycle over jumps with o.g. wario-ware vibes.

Later on, I was able to play some [Dukkido](https://store.steampowered.com/app/3186280/Dukkido/) by
[MariSuCho](https://store.steampowered.com/developer/MariSuCho) - this one is
a bit of a Pikmin/RTS. The controls are reactive, and managing the troops was
smooth and fun. I look forward to getting to sit down with this one later on.
The devs for this game also had some great dev-console moments to show off,
adding and removing various slime moments to make my playthrough more fun.

I didn't get as much Day 2 showcase time, but have to mention
[Dunderbeck](https://store.steampowered.com/app/2477750/Dunderbeck/), which
showed me that at least one way to make sausage is via an inventory-management
autobattler with an awful lot of rats. So there's that.

## Some Day 1 Talks

My favorite talk from Day 1 (and maybe the whole conference?) was
[badcop](https://www.twitch.tv/badcop_)'s mixed-reality streaming hacks. It was
an awesome hack-tale full of ingenuity,
cutting-corners-to-dodge-deeper-rabbit-holes, and some stellar visual results.
One of my TODOs is to hop in that discord and consider hacking on an OSX or
Linux version.

There was solid Godot iOS content on Day 1 - [Xogot](https://xogot.com/) (the
Godot Editor as a native iPad app) and the
[`SwiftGodotKit`](https://github.com/migueldeicaza/SwiftGodotKit) that supported
it. I'll definitely be exploring this more for my ongoing Dot Hop mobile port.

[Jeff Ward](https://www.fuzzybinary.com/index.html)'s overview of impling your
own scripting language for Godot is still hitting me. My brain has been rooting
around, wondering if it's crazy to try to support a clojure-like syntax as a
scripting language for Godot.

> I hacked a .tscn/.tres <> .edn (clojure data format) thing together a few years
> ago called [godot-edn](https://github.com/russmatney/godot-edn)... maybe
> there's something there?

Eric Peterson's ([Baja The Frog](https://linktr.ee/BajaTheFrog)) Event-Bus-Pattern talk was enlightening. I'd
seen a bit about this pattern before, but always shyed away - I assumed I'd need
to implement some fancy library to make it reasonable. Nope! Just an autoload
with some signals on it! I'm hopeful to delete a bunch of defensive code in Dino
:D [Here's a blog post by Eric covering
this pattern](https://dev.to/bajathefrog/riding-the-event-bus-in-godot-ped).

[Rawb Herb](https://rawbherb.art/)'s UI design talk was also awesome, and so was
[Adam Scott](https://adamscott.studio/)'s state of the Godot on the Web. Plus
[Casey](https://bsky.app/profile/naud.bsky.social) + Sarah's Cross-Disciplinary Collaboration tips (and love for
custom resources and in-house tooling). [HP van Braam](https://blog.tmm.cx/)'s
nanotech tool was mind-blowing. So many insights and so much inspiration to
glean!

By the end I was overwhelmed and socially fried, and walked back to the hotel in
the rain, which I rather enjoyed.

## Some Day 2 Talks

I met [Josh](https://joshuagalecki.itch.io/), part of [Tiny Mass
Games](https://tinymassgames.itch.io/), a community of game devs drawing
inspiration from [Sokpop](https://www.sokpop.co/). They align their schedules to
release solo and small-team games every 2 months, then take a month off. What a
great cadence! I'm hopeful to join for Season 11, starting in July!

Chad Stewart's ([binarysolo](https://www.binarysolo.com/)) talk showed some
great mobile game-dev tools, if you lose your laptop to a flood but still need
to make an indie game (e.g. for Tiny Mass). Between this and Xogot, I'm excited to get more time
working on games away from the (proper) keyboard.

[Acerola](https://www.youtube.com/@acerola_t)'s dry sense of humor was a riot,
and now I know that a CE (some shader term) is a generic work load at the
bottom of a GPU's well... or something like that. Mostly I learned that now
Godot can do everything in the known universe, across all space and time,
whereas before things were slightly more limited.

> Digging through my notes, it's a `Compositor Effect` - [here's a tutorial for
> understanding them
> better](https://docs.godotengine.org/en/stable/tutorials/rendering/compositor.html).
> I can't read this without laughing aloud at how lost I feel!
> Maybe this is a good candidate for a future stream or video tutorial.

The Godot AMA was great - too much to capture here. One moment I liked was Emi's
5-years-in-the-future dream: more tools supporting all the disparate use-cases
in the ecosystem. Godot is not trying to grab-all-the-game-devs like some of
its for-profit competitors. The recent rise in popularity has led to problems
of scale, and it is difficulty to support users with such a wide variety of
needs. Some devs want amazing, Unreal-Engine-like 3D, while others just want an
upgraded RPG-Maker.

> Looking for more game engines to poke at? Defold, Bevy, Heaps.io, Luxe, Love2D all
> look pretty cool! If only we had infinite time....

[Chris Ridenour](https://www.linkedin.com/in/cridenour/)'s Multiplayer Dome
Keeper talk was spot on - multiplayer is challenging and complicated, and he did
a great job of unpacking the terms and decisions required to make the magic
happen. I was having flashbacks to working out our approach in [Rapid Eye
Madness](https://store.steampowered.com/app/3248030/Rapid_Eye_Madness/) last
fall - a talk like this would have gotten us all on the same page much more
quickly!

[StayAtHomeDev](https://www.youtube.com/@stayathomedev)'s talk may have been my
favorite overall - the strong throughline was encouragement to be yourself and
to make things that are YOU. He also outlined a reasonable approach to finding
your voice as a Youtuber - it's inspired me to create a few more videos without
sweating the stats. Maybe a T-shirt Tier List, or something fun?

## The Lightning Talks

Finally, there was nothing left but the Lightning Talks. After battling with
nerves the whole conference, they finally reached a head in the last half-hour
before my sentencing.

A major thank you to the other Lightning Talk speakers, who built up some
camaraderie talking about our nerves beforehand.
[Maaack](https://maaack.itch.io/) especially helped me out, recommending a deep
breath and a power pose before we started.

The talk went by in a blur - my personal experience of it was very: "why am I
out of breath?", "what words am I saying?". After the fact, I think it went
pretty well, and I'm excited to watch the recording. I got great feedback and
several people told me they're excited to use
[Log.gd](https://russmatney.github.io/log.gd/#/) in their own projects.

> I've been beefing up Log.gd's documentation lately - feel free to reach out or
> [create an issue](https://github.com/russmatney/log.gd) about whatever!

The other Lightning talks were excellent: [Joesph](https://github.com/JosephHill)'s push into the mobile space,
[Maaack](https://maaack.itch.io/)'s Game Jam experience,
[Matt](https://pondersoft.itch.io/)'s Time Management advice,
[Warner](https://github.com/nonameentername)'s cutting edge Csound integration,
and [Sam](https://szunami.com/)'s Custom Resource clarity. Plus the final tale
from [Annalivia](https://annalivia.xyz/) and [Tyler](https://ambiguous.name/)
about building a game (and arcade cabinet) for a museum - what a great way to
wrap everything up!

I'm pumped to watch all of these again!

## NYC Godot User Group?

A fun occurrence at Godot Con was running into other New York City folks. We
were inspired (jealous?) of the Boston Godot User Group's traction,
and have since started a Discord, laying plans to show-and-tell Godot
things in the Big Apple. I'm pumped to be a part of this new group! Reach out if
you're interested!

Also, more kudos to [Joesph Hill's
talk](https://www.youtube.com/watch?v=hVNQp8ux1iA) covering how the Boston Godot
User Group started, its format, and encouraging more folks to follow their path.

## Some other folks!

I had some great conversations with other folks, and am excited to see what
their futures hold. In particular:

- Tristin at [Splashy Inc](https://splashy-inc.itch.io/) has been creating
  a game per month for a long while, with no end in sight!
- [Phosfi](https://store.steampowered.com/app/2625450/Phosfi/) by Ben at UpRoom
  Games looks amazing!
- It was great to meet talented artists buried among so many programmers, like
  [Eron Hare](https://eronhare.com/).
- Eric and I connected over Chess and Wario-Ware likes, and it turns out
  he's a stellar technical artist (check out [that
  portfolio](https://www.youtube.com/watch?v=c3ACqBg-yAs)! :eyes:)
- I talked only briefly with [David
  (vidvadgames)](https://linktr.ee/vidvadgames), but he seems like a great
  resource re: console ports!
- I'm certainly forgetting other folks here, hopefully I'll update this with
  more in the future as I comb through my notes.

## Other Recaps

Youtube:

- [Interviews with every Showcase Developer](https://www.youtube.com/watch?v=9NJn7dTTZnw)
- [My GodotCon 2025 Recap](https://www.youtube.com/watch?v=KwwMhC_TnjI) by [StayAtHomeDev](https://www.youtube.com/@stayathomedev)

Blogs:

- [An itch.io devlog](https://itch.io/blog/941196/godotcon-boston-2025) by [AsFunAs/DevEarley](https://asfunasfun.itch.io/)
- [Reflecting on Godot
  Con](https://www.essay-games.com/blog/reflecting-on-godotcon/) by [Essay Games](https://bsky.app/profile/essaygames.bsky.social)
- [Exodrifter on showcasing _no signal_](https://www.exodrifter.space/blog/20250516012109)
- [Snoozy Kazoo's hilarious Godot Con wrap-up](https://snoozykazoo.com/news/2025/05/23/may-devpost)

## T'was a blast!

And now, things are settling back down. No more Godot talks to go to, just a ton
of steam games, itch projects, github urls, and blog posts to wade through.

`Thank you x 1,000,000` to the volunteers, coordinators, organizers, speakers,
attendees, etc. The conference was a major success, and I mean to capitalize on
all the energy I got out of it!

Thank you all, and hope to see you again soon!

