# Glossolalia Listen Prototyping

Glossolalia is a new project I'm working on with some talented folks.

It's in a very early stage - right now we're exploring mechanic ideas.

I enjoyed hacking this 'Listen' feature together, and adding some bells and
whistles along the way.

I'm including timestamps b/c I'm obsessed with time tracking and want to reflect
on it - could I have gotten away with less time spent? Should I invest more? How
long does it take to do things?

I was heads down on this from noon to five or so - usually I try to break work
sessions up with pomodoros, but I did not do that here.

I'd be cool to intersperse commits here as well!

--

1pm: A basic listen prototype!

<video controls width="80%" height="400px">
    <source
    src="../_images/glossolalia/listen prototyping 2024-12-11 13-07.mp4"
    type="video/mp4"
    />
</video>

<video controls width="80%" height="400px">
    <source
    src="../_images/glossolalia/listen prototyping 2 2024-12-11 13-10.mp4"
    type="video/mp4"
    />
</video>

We see the camera zooming out when we hold `L` on the keyboard.

--

2pm: enemies <> listen area proof of concept.

<video controls width="80%" height="400px">
    <source
    src="../_images/glossolalia/listen enemy fade in-out 2024-12-11 14-01.mp4"
    type="video/mp4"
    />
</video>

The enemies fade in and out based on whether they intersect an invisible growing
circle around the player.

--

2:15: a basic light scale along with the zoom out.

<video controls width="80%" height="400px">
    <source
    src="../_images/glossolalia/listen including light scale 2024-12-11 14-14.mp4"
    type="video/mp4"
    />
</video>

Scaling the light on the player along with the listen area's size.

--

2:45: dropped in a blur shader

<video controls width="80%" height="400px">
    <source
    src="../_images/glossolalia/listen first blur shader 2024-12-11 14-43.mp4"
    type="video/mp4"
    />
</video>

You can see EVERYTHING is blurred, which is not ideal.

--

3pm: Now blurring around the edges.

<video controls width="80%" height="400px">
    <source
    src="../_images/glossolalia/listen with fade light blur 2024-12-11 15-04.mp4"
    type="video/mp4"
    />
</video>

It's not super easy to see, but the 'clear' (read: not blurry) area grows along
with the light + listen areas.

--

3:20: Experimenting with slow mo

<video controls width="80%" height="400px">
    <source
    src="../_images/glossolalia/listen with slowmo 2024-12-11 15-22.mp4"
    type="video/mp4"
    />
</video>

Maybe we want to include some slow-motion while listening? I ended up removing
this later.

--

4pm: Yell capture (with screenshake!)

<video controls width="80%" height="400px">
    <source
    src="../_images/glossolalia/first yell capture 2024-12-11 16-03.mp4"
    type="video/mp4"
    />
</video>

Another mechanic we're considering along with 'Listen'.

I'm particularly happy with this one because this screenshake is an old
algorithm of mine refactored to work with [Phantom
Camera](https://github.com/ramokz/phantom-camera).

--

5pm: Listen reveals coins, and a more realistic 'Gym'

<video controls width="80%" height="400px">
    <source
    src="../_images/glossolalia/listen shows coins and player respawn 2024-12-11 16-47.mp4"
    type="video/mp4"
    />
</video>

<video controls width="80%" height="400px">
    <source
    src="../_images/glossolalia/listen coins and respawns 2024-12-11 16-49.mp4"
    type="video/mp4"
    />
</video>

<video controls width="80%" height="400px">
    <source
    src="../_images/glossolalia/listen gym two playthrough 2024-12-11 16-51.mp4"
    type="video/mp4"
    />
</video>

Now showing the features in a new space to see how they feel.

I like the way I'm getting surprised by the enemy when I'm impatient here -
something about this seems to be working!

--

That's it for this devlog! Thanks for checking it out!
