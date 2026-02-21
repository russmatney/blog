---
title: ""Mobile Writing (Capture) Tools""
date: 2020-02-17
category: getitwrite
tags: [writing]
---



I've struggled in the last few years to use my phone for anything productive.
Compared to sitting at my laptop, with vim or emacs bindings available, or
organizing my brain with Org-mode, using my phone for any input feels like a
waste of time, except for selecting a song on Spotify or consuming emails or
articles.

But that can't be the way - surely there are people who are more than happy with
getting things done on a phone. I decided to look into it - this post covers
tools that ended up in my current writing stack.

Some personal context for what I'll cover:

- I enjoy the principles of [Getting Things
  Done](https://gettingthingsdone.com/) by David Allen, so am mostly focused on
  tools that support Capture.

> GTD in a nutshell: rather than interrupting the current task, Capture the
> thought in an Inbox, and continue Executing. Later, review your Inboxes.

- I'm heavily invested in Emacs, and that influenced my search and this post.

- Things I'm targetting with my writing:

  - Blog posts
  - Stories, Poems
  - Comics
  - Video Games
  - Journal

- I'm on iOS on mobile and Linux on the desktop, and can't speak much to other
  OSs at this point.

I think of writing as thinking aloud - write first, ask questions later. I
want to be able to just spill at any point, and nothing should stand in my
way. So how do you spill?

## The Tools

### Beorg - Mobile Org-mode support

[Beorg](https://beorgapp.com/) is a mobile app that can sync with your emacs
Org-mode directory, pulling agenda views and allowing for full editing of your
files. It also supports capture (and capture-templates) for immediately
adding items on the go - note that this includes a share-sheet for adding
anything else to your Org inbox across your iPhone. The app is free, but has
one-time paid upgrades available.

Some resources:

- The [Beorg user guide](https://beorgapp.com/manual/) is a great piece for
  familiarizing. And it's the reason for this post because it
  led me to MindNode and Drafts, which I'll get into below.
- [Beorg Forums](https://appsonthemove.freshdesk.com/support/discussions)
- [Beorg Screencasts](https://beorgapp.com/screencasts/)

Beorg unlocked me in a large way - being able to find and review notes on the
subway was a big win. The capture button is the must-have
feature and the reason I got it in the first place, but Beorg even supports a
lispy scripting language, and has a REPL!

I've integrated my Org directory with Dropbox, and use the Beorg Dropbox integration
to keep things in sync.

### MindNode - for structured, mobile brain-dumps

One thing I enjoy about Org-mode is the way you can easily nest thoughts
and tangents - writing is a flow, and it's important to capture but not get lost
in the meandering mind. Can that be done on mobile?

[MindNode](https://mindnode.com/) is the closest I've found so far. It's an app
for visual brainstorming - it lets you write mind-maps. You start with one node,
and can add subnodes, then subnodes to those, all the way down. Each node can be
text, and can have attachments. It's all displayed in a balanced graph, with
nice colors and UX for writing, editing, and adding more.

I found the interface quite reasonable for thinking aloud. To get more familiar
with it, I started by creating at least one mindnode per day - this post was
actually the result of one of these daily mindnodes.

<iframe src="https://my.mindnode.com/pJ1TeN5nRXefUnwu1gqZFsB1eEJekL5Xqcygckbp/em#28,32,-2" frameborder="0" marginheight="0" marginwidth="0" style="border: 1px solid rgb(204, 204, 204); width: 700px; height: 300px;" onmousewheel=""></iframe>

I started out trying to get to a "Published Blog Post", capturing all the things
necessary to get the blog back in order. This style of adding things to lists
helped me enumerate the issues without getting stuck on any. You can see I
wandered into the never ending rabbit hole of Blog Engines, but finally managed
to reach the "Content" node.

I was able to embed this through MindNode as well - they offer hosting and
provided an iframe UI. Though, as I'm writing this and poking into it, it looks
like they may be shutting down the my.mindnode service that provided it. They
also [just switched to a subscription
model](https://mindnode.com/post/mindnode-7--visual-tags-and-business-model-changes).

To the next tool!

### Drafts

[Drafts](https://getdrafts.com/) is an app focused on getting you writing
immediately. It defaults to being opened in an empty page, with a blinking
cursor ready for input. It is intended to be used for quick capture and drafting
out thoughts and ideas.

Drafts' actions model supports a variety of integrations, and the actions
themselves are quite editable. I was able to add a custom Dropbox action that
uploads and appends my drafts to a `drafts-journal.txt` file in dropbox, along
with a timestamp.

Drafts also supports some better text editing functions, like moving
forward/backword per-word (vim or emacs-ish!). I don't often use those features,
as often the writing is forward-only and that kind of editing would be better
applied to maintaining/editing a doc. Still though, I can tell they're in my
mind-space.

I'm sure I could be getting more out of Drafts - it's quite feature-full!

Similar to MindNode, I'd recommend building the habit with at least a Draft per
day. Even just a silly poem is worth it!

## From here, it's just processing the input

Once you're capturing your thoughts, what do you do with it all?

I wish I had a better answer here - the best advice is probably to drive toward
finishing things. I still get caught up thinking about where files should live
if they're in-flight. Do I want to edit content in Org-mode or a blog-ready
markdown file?

In the case of this post, I outlined in MindNode, waited 3 months, then got up
and wrote it start to finish in an hour, all markdown. No Org-mode or editing
process necessary. Maybe I'd benefit from one?

The thing to be aware of: capture tools and solid processes are good, but can
also be overwhelming - soon you'll have created more work than you know what to
do with.

It's important to be free when processing your inboxes - delete things
often! If your tools are good and the idea is important, it'll come back. The
point is to maintain focus, not do 100 extra noise-tasks.
