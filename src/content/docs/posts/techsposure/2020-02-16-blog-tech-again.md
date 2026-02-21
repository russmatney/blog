---
title: "Blog Tech, Again"
date: 2020-02-16
category: techsposure
tags: [tech]
---



I usually tell people not to write posts that say "Hey, I'm blogging again!".

Reasons:

- Newcomers probably don't care or have context
- It's going to look weird if that's the only post

Hopefully that's not the case two months from now! Instead of a
post about blogging again, I'm going to cover some of the blog tech I used to
get this thing running again.

### Switched to Gatsby

I finally settled on this [chronoblog
theme](https://github.com/Chronoblog/gatsby-theme-chronoblog/) and
[Gatsby](https://github.com/gatsbyjs/gatsby) to support this. Years ago (8!) I
wrote [a post about blog
tech](http://russmatney.com/techsposure/2012-10-20-blog-tech/) - it's quite
outdated. Since then, I went through a Jekyll and then
[Hugo](https://gohugo.io/) phase ([blog repo
here](https://github.com/russmatney/hugo-blog)).

Ultimately, the feature set I want is:

- A simple, clean, text focused theme. No bells or whistles.
- Reasonable search and filtering by tags. I want it to be easy to find my own
  posts.

I did end up editing post metadata in all my old posts,
mostly cleaning up excessive tag usage.
Now I'm finally writing a post again!

> One hang up I'm struggling with is whether I should outline/write in org mode
> or jump straight into markdown blog files... For now I've identified that as a
> sneaky, writer's-blocky excuse.

To update the years-old blog-tech post and get my own notes recorded,
here's a list of blog engines in consideration when I made this switch:

- A new Hugo Theme - [Pure](https://themes.gohugo.io/hugo-theme-pure/) or
  [Pulp](https://themes.gohugo.io/pulp/) - I liked these, but a few errors and
  extra pages/features weren't easily excised, and I wasn't satisfied.
- A brief start with [eleventy](https://www.11ty.dev/), which I'd never heard of.
- Eleventy has a nice [list of
  competitors](https://www.11ty.dev/docs/#competitors) if you're looking for
  others.

I decided to try out Gatsby, which is a NodeJS + graphQL-based platform. I
feared that tool chain might be overkill for getting a simple blog done (the
graphQL in particular led to some [inscrutible
errors](https://github.com/Chronoblog/gatsby-theme-chronoblog/issues/21)), but
the theme provided very simple pieces and introduced me to the `.mdx` extension,
i.e. `.jsx` + `.md`. It turned out to be simple to make a few tweaks of my own,
which is a good sign - maybe I'll be able to maintain this with little effort!

### Deployment: S3

After all these years, I'm still just pushing the build to S3.
After configuring gatsby-cli and aws-cli, that just means:

```sh
$ gatsby build

$ aws s3 sync public/ s3://russmatney.com
```

Now, isn't that nice?
