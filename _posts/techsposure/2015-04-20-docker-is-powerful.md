---
layout: post
title: 'Docker is powerful - some thoughts and a mini-overview'
categories:
- Techsposure
tags:
- docker
- machine
- compose
- swarm
- consul
- registrator
- system administration
- wordpress
type: post
---

###*TL;DR*:

I have no real sys-admin experience,
but was able to get a cluster of Docker containers running without too much trouble.

[Docker](https://www.docker.com/)
(plus [Docker-Machine](https://docs.docker.com/machine/),
and [Docker-Compose](https://docs.docker.com/compose/)),
[Consul](https://www.consul.io/),
and [Registrator](https://github.com/gliderlabs/registrator)
are a sweet suite of tools for maintaining your app in the cloud.

The [Dockerfile](https://docs.docker.com/reference/builder/)
is an awesome, unified abstraction
that exposes the definition of a container.

---

##What the derp is Docker?

Docker is a platform for running apps on a computer.
It's a binary that you install wherever you want to run your apps,
and once installed, you can install "containers" defined by Dockerfiles.

These containers can interact in a number of ways,
depending on what you want to do.

My experience is predominantly web-dev,
so I wanted to run a
[Node](https://iojs.org/en/index.html)
app that touched [RethinkDB](http://rethinkdb.com/),
[Redis](http://redis.io/),
and a front-end [AngularJS](https://angularjs.org/)
app.

You can do all these things without Docker, right?
Sure, you can lean on Heroku and plugins,
or you could script out and install all these things yourself on your own machine somewhere.
And it'd probably work great.

##Dockerfiles rock.

The advantage of Docker is the Dockerfile –
in one place, you define the exact spec for one of your containers.

That file can be used to build the container from scratch,
and it'll run the same every single time,
whether your app is building in Dev, in Staging, or in Production.

This is great for aligning the environment your app runs in,
but it also implies something powerful:
container throw-away and rebuild is super cheap and super clean.
Should something go awry on your system,
you can easily remove and rebuild any and all of it.

This is pretty useful if you're like me,
trying to figure out how to get this black-box cloud computer to listen to my commands.

Also, coming from a sheltered Heroku up-bringing,
being able to read Dockerfiles is an awesome way to learn how these systems are composed.

So, Dockerfiles for my system:

- [Node (iojs)](https://github.com/iojs/docker-iojs)
- [RethinkDB](http://dockerfile.github.io/#/rethinkdb)
- [Redis](http://dockerfile.github.io/#/redis)
- [Apache (static front-end)](https://registry.hub.docker.com/_/httpd/)

Much thanks to the [Dockerfile Project](http://dockerfile.github.io/)
for pulling together an awesome set of resources.

##Some tools of the Docker trade

If you'd like to dive into Docker,
I highly recommend going through the entire
[User Guide](https://docs.docker.com/userguide/).

Docker provides a CLI,
and recently launched a few tools that make it even easier to work with.

###Docker Machine

[Docker Machine](https://docs.docker.com/machine/)
is a CLI for interacting with machines you are running Docker on,
be it virtual machines on your own computer or machines off in the clouds.

Creating a new, Docker-running machine is as easy as:

    docker-machine create --driver virtualbox dev

###Docker Compose

[Docker Compose](https://docs.docker.com/compose/)
is a nice, version-controlled way to handle your `docker run` commands
and improve your logging/debugging workflow.

This lets you control and log a slew of containers at once with commands like
`docker-compose up` and `docker-compose logs`,
which is much more convenient than the drawn out flags and vars of long `docker run`s.

##Consul + Registrator

Being new to this kind of development,
there are still concepts that I'm wrapping my head around.
[Service Discovery](http://en.wikipedia.org/wiki/Service_discovery)
is one of these, and [Consul](https://www.consul.io/)
(plus a tool called [Registrator](https://github.com/gliderlabs/registrator))
provides a nice solution for it.

The gist of Service Discovery in this context is that
the containers we're running on our machine(s)
need to know how best to communicate.
Our Node container needs to know what ports to send data to the RethinkDB container on.

If you're running on one machine, something like Consul + Registrator may be overkill.
But if you're not - Consul does some very cool things.

For example, Failure Detection.
If we're running a cluster of RethinkDB containers,
and one of these containers kicks the bucket,
Failure Detection allows Consul to no longer direct requests to that container,
with minimal work on our part
(essentially just defining a 'Failure' for that container).

[Registrator](https://github.com/gliderlabs/registrator)
is a service registry bridge -
it automatically publishes/unpublishes the services your Docker containers expose.

If you're only running on one machine and don't want to get into this complexity,
you'll want to look into
[container linking](https://docs.docker.com/userguide/dockerlinks/).

Otherwise - my consul + registrator setup led to this current docker-compose file:

    consul:
      image: progrium/consul
      command: -server -bootstrap -advertise 192.168.99.101
      ports:
        - "8300:8300"
        - "8301:8301"
        - "8301:8301/udp"
        - "8302:8302"
        - "8302:8302/udp"
        - "8400:8400"
        - "8500:8500"
        - "172.17.42.1:53:53/udp"
      dns:
        - 172.17.42.1
        - 8.8.8.8

    registrator:
      image: gliderlabs/registrator
      command: consul://192.168.99.101:8500
      volumes:
        - /var/run/docker.sock:/tmp/docker.sock


I ran into a number of issues getting all of this running,
but predominantly I needed to learn how all these things work together.
I'm in a better place now,
and will spare the post the details.

Feel free to reach out if you're having trouble getting going,
and I'll try to help.

##An aside: a Wordpress success story

Just a quick aside about the convenience of Docker.

A new contract came along,
and I had to run Wordpress locally to do the work.

I've run Wordpress before, but it's been a while, and
I thought I'd have to face the details around Mamp/php/mysql/whatever.
All of which is fine, but it's a headache that isn't really related to doing the work.

Docker to the rescue!

Here's an awesome [Wordpress Dockerfile](https://github.com/eugeneware/docker-wordpress-nginx).
That plus Docker Machine lets you go from 0 to locally running Wordpress in just a few minutes,
and lets you push up your own wordpress instance to any machine in the same amount of time.

##Beast mode

Going forward, I'd like to take on [Docker Swarm](https://docs.docker.com/swarm/).
It's a tool that makes it easy to control big swaths of containers all at once.

Aka, Beast mode.

