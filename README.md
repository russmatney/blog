Danger Russ Blog
====

A devlog and portfolio site.

I've over-engineered my personal site a bunch of times - this time i'm trying to
support the old content AND make devlogging minimal-effort.

Check out the 100-word stories from way back, and hopefully soon I'll pull in
some poetry and fiction doodles from over the years.

# tools

This blog is built with docsify! It's a simple and modern way to host a bunch of
markdown as a website, and I quite like it.

The sidebars/indexes are generated with clojure (babashka) - see bb.edn and
`bb/blog/*.clj` for the code.

#### docsify links

Some helpful links collected while reading through docsify's documentation.

- [Configuration docs](https://docsify.js.org/#/configuration)
  - writing a plugin: https://docsify.js.org/#/write-a-plugin

  - [routers](https://docsify.js.org/#/configuration?id=routes)
    Docsify supports dynamic, async route handling, like a full-fledge frontend
  - [vueComponents](https://docsify.js.org/#/configuration?id=vuecomponents)
    First class support for vueComponents/mounts/global options, for quick interactivity
    might come in handy?

    One idea is a git-commit component, or maybe something for garden-link-previews
- [General tips](https://docsify.js.org/#/helpers)
- [Plugins](https://docsify.js.org/#/plugins)
- [Awesome Docsify](https://docsify.js.org/#/awesome)
  - [create-docsify-plugin](https://github.com/corentinleberre/create-docsify-plugin)
  - reference for hundo counts: [docsify-count](https://github.com/827652549/docsify-count)
  - flexible quotes: https://github.com/fzankl/docsify-plugin-flexible-alerts
  - gif control: https://gbodigital.github.io/docsify-gifcontrol/#/
  - image caption: https://h-hg.github.io/docsify-image-caption/#/
  - animated terminal: https://github.com/sxin0/docsify-termynal
  - per page history: https://github.com/simochee/docsify-plugin-page-history
  - navbar fixed: https://github.com/LIGMATV/docsify-navbar-fixed
  - breadcrumbs: https://github.com/FranCarstens/docsify-breadcrumb
  - More Themes: https://docsify.js.org/#/awesome?id=themes
