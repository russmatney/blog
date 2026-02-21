---
title: "'Updating Protobuf and GRPC in Golang'"
date: 2016-07-09
category: techsposure
tags: [Techsposure, tech]
---



###*TL;DR*: When protobuf updates, all the `.proto`s and deps need to update.
This post is a quick-lookup for how to do that so I'm not googling it every time.

---

##Go Dependencies

Update your relevant go dependencies:

```sh
go get -u github.com/golang/protobuf/{proto,protoc-gen-go} && \
go get -u google.golang.org/grpc
```

##Update Protoc

Then (or before) download and install the latest protoc binary: [github.com/google/protobuf/releases](https://github.com/google/protobuf/releases).

Download the zip, open it, and move the `protoc` executable file into somewhere on your path.

```sh
mv ~/Downloads/protoc-3.0.0-beta-3.1-osx-x86_64/protoc /usr/local
```

##Rebuild where necessary

Rebuild your `.protoc` files, likely per project.

For me, this means running `go generate ./...` in each go project's root directory.

