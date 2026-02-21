---
title: "'How I rebuilt my dotfiles'"
date: 2014-02-09
category: techsposure
tags: [Techsposure, tech]
---



###*TL;DR*:

It is so important to own your workflow - I just took mine back.
This post is the snapshots of my dotfile reconstruction:
vim, tmux, vundle, etc.

---

When I started at [Moveline](https://www.moveline.com),
I also started using vim, per the advice of my mentor, [Chris Garvis](github linky).
He asked if I wanted it easy ([sublime]()) or the hard way ([vim]()).
After a few days of pairing, I essentially forked his dotfile repo
and about a week or two later, I started to feel like a magician.

I stuck with Vim happily until we switched from Backbone to AngularJS and
CoffeeScript, at which point I felt I moved back to Sublime, whining about
file-trees and Sublime's multi-select magic.

Since then, things have been fine.
They were ok, you know. I was Cmd + D -ing up a storm in Sublime land.
It felt good!

Then, it's late October, and Ryan is telling me to take the dive again.
"NoVIMber!" he says. "You'll thank me later!" he says.
But I am lazy. I want to own my dotfiles. I want to take them back.
I know this will require effort, and thinking.
It doesn't happen.

A few weeks ago I was putting together some e2e tests in Angular,
and wanted to show of my mad Protractor skills to Ryan and Andrew in a
coffeeshop.
When I opened Sublime, there was an audible gasp,
then there was the gnashing teeth and the grinding bones.
Someone sitting two tables away dropped their latte in a clatter.
I was embarrassed, and they shooed me away.

I decided I couldn't wait any longer.
I decided this whole thing was bigger than myself.
I decided it was time.

---

###Tear Down

First step - wipe everything.
I cleared out as much as I could from the old remnants.
Over the next weekend/week, I ironed out as much as I could.

Below is a walkthrough of how I built them up,
intended as a guide for anyone that it may help.
I tried to keep the commits clean.

[Here's the full list of
commits](https://github.com/russmatney/dotfiles/commits/master).
