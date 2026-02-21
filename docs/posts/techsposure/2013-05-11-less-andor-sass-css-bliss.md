---
title: ""LESS and/or Sass = CSS bliss""
date: 2013-05-11T00:00-04:00[America/New_York]
category: techsposure
tags: [Techsposure, tech]
---


What's up code-monkeys? This post is all about <a title="Wikipedia - Cascading style sheets" href="https://en.wikipedia.org/wiki/Cascading_Style_Sheets">CSS</a>. In just a few minutes, you'll be able to style with, um, style.

For the first few months of my hack of a web development career, I've been writing CSS the way I thought it had to be: pure CSS. Different projects had different levels of organization, from well-named, modular files to disgusting, hundred-line Frankenstein monsters. I'd heard of CSS preprocessors, but hadn't gotten into them. It's definitely important to learn the basics, sure. But once you can ride a bike, you might as well get a motorcycle. Especially if it's free, downloads in seconds, and makes you a much better, um, bicyclist.

The two most popular preprocessors are <a title="LESS CSS" href="http://lesscss.org/">LESS</a> and <a title="Sass CSS" href="http://sass-lang.com/">Sass</a>. Choosing between them is not as important as just picking one and using it. They both have several awesome features that will immediately speed up your styling. I'll briefly get into three of them: <strong>nesting</strong>, <strong>variables</strong>, and <strong>mixins</strong>.

HTML is <strong>nested</strong>, so why shouldn't your stylesheet be? There are examples on both websites, but here's the gist. This:

{% highlight css %}
.post{
	color:black;
}
.post a{
	text-decoration:none;
}
.post a:hover{
	color:green;
}
{% endhighlight %}

can be written like this:

{% highlight scss %}
.post{
	color:black;
	a{
		text-decoration:none;
		&:hover{
			color:green;
		}
	}
}
{% endhighlight %}

So that, logically, styling the `<a>` tags inside the class `.post` is handled <em>inside</em> the styling of `.post`. In both LESS and Sass, the `&` is used to append other classes or states (ex: `:hover`) onto whatever element you are already styling.

<strong>Variables</strong> do exactly what they should. You can assign any value at the top of your stylesheet, and use it throughout your stylesheets. No more copying and pasting of color-specific hex codes, or hoping you set all the fonts, line-heights, and border-radii properly.

And if that doesn't convince you, <strong>Mixins</strong> will. Mixins act like variables for full styles, rather than just values. They are simple to write, and you can reuse them everywhere. The example from the LESS homepage uses Mixins and an @radius variable. Instead of this:

{% highlight css %}
#header {
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	-ms-border-radius: 5px;
	-o-border-radius: 5px;
	border-radius: 5px;
}
#footer {
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	-ms-border-radius: 10px;
	-o-border-radius: 10px;
	border-radius: 10px;
}
{% endhighlight %}

You can create this mixin:

{% highlight scss %}
.rounded-corners(@radius: 5px) {
	-webkit-border-radius: @radius;
	-moz-border-radius: @radius;
	-ms-border-radius: @radius;
	-o-border-radius: @radius;
	border-radius: @radius;
}
{% endhighlight %}

And apply it to elements like this:

{% highlight scss %}
#header {
	.rounded-corners;
}
#footer {
	.rounded-corners(10px);
}
{% endhighlight %}

And on top of writing your own mixins, there are of course many that have already been written available all over the web. Here are <a title="Less elements" href="http://lesselements.com/">some for LESS</a>, here are <a title="Compass Sass" href="http://compass-style.org/">some for Sass</a>. As <a title="CSS Tricks" href="http://css-tricks.com/">Chris Coyier of CSS Tricks</a> says, preprocessors make CSS fun again.

I recommend both LESS and Sass. They both come with more than I mentioned here, including nice-sounding things like functions and inheritance. If you're command-line/terminal shy, you might have an easier time starting with LESS, because you can compile it in the browser (just add the LESS compiling script to your HTML). At the same time, I'd encourage you to cozy up to the terminal soon. You'll be glad you did.

If you're really gun-hoe about Sass, good for you! :) Checkout <a title="Compass sass" href="http://compass-style.org/">Compass</a>. It's a ruby-gem that provides you with a ton of CSS3 mixins right out of the box. Chris Coyier says Sass + Compass is the best way to CSS, and I trust that man. I'm using this combo to build a Wordpress theme now, to replace the default I've been riding for the last few months. (If you're interested, I'm starting from <a title="Underscore S" href="http://underscores.me/">a design-stripped theme called _s</a>.)

So, in summary, preprocessors are awesome. Use them in your next (all your future) project(s). Want to get even better? Learn how to use <a title="Twitter Bootstrap" href="http://twitter.github.io/bootstrap/">Twitter Bootstrap</a>. Read the documentation! It's a powerful scaffolding tool and component base for any website's style needs, is fully responsive, and is written entirely in LESS.
