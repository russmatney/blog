---
layout: post
title: 'Unparsable JSON (null bytes) bug'
categories:
- Techsposure
tags:
- Golang
- Elasticsearch
type: post
---

###*TL;DR*:

---


TLDR: a well-composed elasticsearch query is a beautiful thing, but it's easy to get lost on the way there. This post is one path. Stay clear-headed from the top down and you should be fine.

Declare es and go version

The mappings and data set

the desired query

A well named search struct

Use that Boolean

Unnested

Nested

Proper ranking via subdocs

TLDR: couldn't get to the bottom of a huge unparsable json output file - turned out to be some null bytes - wasn't resetting the cursor after truncating the file before writing the closing bracket
