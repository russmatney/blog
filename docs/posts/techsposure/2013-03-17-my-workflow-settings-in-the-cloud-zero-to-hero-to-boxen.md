---
title: ""'My workflow settings in the Cloud: Zero to Hero to Boxen'""
date: 2013-03-17T00:00-04:00[America/New_York]
category: techsposure
tags: [Techsposure, tech]
---


Getting a new computer up to speed can take hours or even days. Finding the time to do so without cutting into your own productivity can be an issue.

I just got a new computer (Macbook Pro 13", non-Retina) a few weeks ago, and had been keeping an exhaustive list of everything I did to it, thinking it'd be a helpful tool for anyone interested and my next computer. Making this list seemed like a painful but one-and-done process. That, however, was before we started using <a title="Boxen" href="http://boxen.github.com/">Boxen</a>.

Our <a title="Chris on twitter" href="https://twitter.com/cgarvis">Lead Software Engineer</a>, (my boss) set our engineering team up with Boxen, which is an amazing tool from Github that makes it easy to bring a new Mac up to speed. It uses Git and <a title="Puppet" href="https://puppetlabs.com/puppet/what-is-puppet/">Puppet</a> to automate the process of bringing any computer up to date with your personal settings. Once your team's boxen is set up, all it takes is a curl call and a "boxen" command in the terminal. Not only does it pull in personal dotfiles and mac preferences (such as hot corners and dock location/size), it can also pull in and update git repos and native applications. <a title="Boxen" href="https://github.com/boxen">Checkout the applications</a> that boxen has available already (or grab the <a title="Puppet Template" href="https://github.com/boxen/puppet-template">template</a> and create your own).

So, right now, I can jump on any computer, set up boxen with a curl call, sign into github, and run "boxen." This command will install the four or five repos I work with every day, my dotfiles (with tons of vim settings), and <a title="Dropbox" href="https://www.dropbox.com">Dropbox</a>, <a title="Sparrow" href="http://sparrowmailapp.com/">Sparrow</a>, <a title="Spotify" href="https://www.spotify.com/">Spotify</a>, <a title="Sublime Text 2" href="http://www.sublimetext.com/2">Sublime Text 2</a>, <a title="HipChat" href="https://www.hipchat.com/">Hipchat</a>, <a title="iTerm2" href="http://iterm.sourceforge.net/">iTerm2</a>, and <a title="Google Chrome" href="https://www.google.com/intl/en/chrome/browser/">Google Chrome</a>. That's incredible.

After that, if you stay up to date with your boxen repo, your personal dotfiles repo, and you create puppet packages for all your applications (I need to add one for <a title="Evernote" href="http://evernote.com/">Evernote</a>, for sure), you'll be ready to jump to any new computer and get everything running in minutes. Your computer is waiting for you, wherever you go, in the cloud.

Now for my lame Zero-to-Hero list. Here are the highlights.

Applications: <a title="Google Chrome" href="https://www.google.com/intl/en/chrome/browser/">Chrome</a>, <a title="Xcode" href="https://developer.apple.com/xcode/">Xcode</a> (and the Command Line Tools), <a title="iTerm2" href="http://iterm.sourceforge.net/">iTerm2</a> (with tmux), <a title="HipChat" href="https://www.hipchat.com/">HipChat</a>, <a title="Evernote" href="http://evernote.com/">Evernote</a>, <a title="Dropbox" href="https://www.dropbox.com">Dropbox</a>, <a title="BetterSnapTool" href="https://itunes.apple.com/ca/app/bettersnaptool/id417375580?mt=12">BetterSnapTool</a>, <a title="Sublime Text 2" href="http://www.sublimetext.com/2">Sublime Text 2</a>, <a title="Flux" href="http://stereopsis.com/flux/">Flux</a>, and <a title="Spotify" href="https://www.spotify.com/">Spotify</a>.

Via the terminal and then via homebrew: <a title="Homebrew" href="http://mxcl.github.com/homebrew/">Homebrew</a>, <a title="Nodejs" href="http://nodejs.org/">Node</a>, <a title="npm" href="https://npmjs.org/">npm</a>, <a title="rvm" href="https://rvm.io/">rvm</a>, <a title="CoffeeScript" href="http://coffeescript.org/">CoffeeScript</a>.

For my vim, bash, and zsh plugins/configs, checkout my <a title="Russell matney dotfiles" href="https://github.com/russmatney/dotfiles">dotfiles</a> here. Note that all credit for these so far is due to <a title="Chris Garvis Dotfiles" href="https://github.com/cgarvis/dotfiles">Chris Garvis</a> and <a title="Useful Vim Plugins - Stack Overflow" href="http://stackoverflow.com/questions/3173963/useful-vim-plugins-for-web-development-and-design-php-html-css-javascript">this post on StackOverflow</a>. If you're going to install a handful of vim plugins, remember to grab <a title="Pathogen - Vim" href="https://github.com/tpope/vim-pathogen">pathogen</a> first. It'll make your life much easier. I'll get into a few of the plugins I love in a later post, but for now, for you front-end vim developers, checkout <a title="ZenCoding" href="https://github.com/mattn/zencoding-vim">Zen-coding</a>.

Happy Coding, code monkeys. And Happy St. Patrick's Day!
