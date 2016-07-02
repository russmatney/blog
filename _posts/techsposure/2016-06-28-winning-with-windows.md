---
layout: post
title: 'Fast App/Window Control'
categories:
- Techsposure
tags:
- Productivity
type: post
---

###*TL;DR*:

- `command` + `tab`: Rotate through open apps (+ `shift` to reverse the direction)
- `command` + `~`: Rotate between the current app's windows (+ `shift` to reverse the direction)
- `command` + `h`: Hide the current application
- `command` + `w`: Close the current window
- `command` + `q`: Quit the current app.

If you're power hungry:

- [BetterSnapTool](https://itunes.apple.com/de/app/bettersnaptool/id417375580?mt=12): Windows-7 style resizing contro
- [Slate](https://github.com/jigish/slate) or [HammerSpoon](http://www.hammerspoon.org/go/): for scripting the commands of your dreams dream-come-true commands customizing to the nth degree via scripts

---

##Multi-tasking? Single task, but still Multi-Apping.

Moving between apps is a huge pain point of a productive workflow.
There's just not much real estate, and only one plane to show it on.

Key to the short-cuts listed here is understanding the current context, that is,
what app are you currently 'in'. The current app is **always** the one listed by
name in the upper left (next to the Apple logo).

This is true **regardless** of what window appears to be on top of the screen.
Be wary of this! Sometimes you can unwittingly Quit the wrong app entirely!

###`Command` + `Tab` = your new bfff.

Right now:

1. Hold `Command`
2. Press `Tab`
3. Don't let go of `Command`

You'll see a happy little list of icons before you,
and notably, the second icon is selected.

These are your open applications, in the order of your most recent use of them.
Whichever of these is selected when you let go of command becomes the app currently in focus.

- You can move between these by tapping `Tab` repeatedly.
- You can reverse the direction by holding `Shift` while pressing `Tab`.
- You can even just mouse over one and release command to move to that app.

If the app is another space, or full-screen mode, you'll move moved to that
location (as long as the app has an open window).

####Pro tips:

- You don't have to wait for the icons to show up - a quick `Command` + `Tab` +
  release will toggle you quickly between the top two applications.
- You can even use this while you're drag + dropping a file from/to your desktop,
  between Finder windows, or between supported applications.

###`Command` + `~` (tilda)

If you have many windows open for the same application, `command` + `~` (reverse with `shift`)
will rotate those windows to the top of the stack.

Quite useful if you want to break your chrome tabs out into different buckets.

###Hide: `Command` + `h`

This hides the current application from view (including `Mission Control`!).
Great for dropping Messages or Slack into the abyss when your boss is behind
you.

Don't worry, you can easily restore it with `Command` + `Tab` and selecting it.
Like it was never gone.

###Quitting isn't for Closers

- `command` + `w`: Close will close a window without closing the app. (Think `w`
  for "Window"
- `command` + `q`: Quit will close all the windows and exit the app, hopefully
  with a warning, but usually not.

##Powerups

  - [BetterSnapTool](https://itunes.apple.com/de/app/bettersnaptool/id417375580?mt=12):
    I used this endlessly until I moved to HammerSpoon (see below). Drag to any side/corner to smart-resize, and configure however you'd like.
    Lately I've set `control` + `shift` + `Arrow left (or right)` to move the
    application to that half of the monitor.
  - [Slate](https://github.com/jigish/slate) or [HammerSpoon](http://www.hammerspoon.org/go/):
    These requires writing your own config file, definitely recommend if you're willing to get into the weeds - reach out and i'm write a guide for each!
    My HammerSpoon config can be found [here](https://github.com/russmatney/dotfiles/blob/master/hammerspoon/init.lua).
