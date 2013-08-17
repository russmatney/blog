---
layout: post
title: Uncle Bob Demands Professionalism and Test-Driven Development
categories:
- Techsposure
tags:
- bob martin
- professionalism
- TDD
- technology
- test driven development
- test driven development tdd
- uncle bob
status: publish
type: post
published: true
meta:
  _publicize_pending: '1'
  _wpas_done_1917337: '1'
  _wpas_mess: Uncle Bob Demands Professionalism and Test-Driven Development
  _publicize_done_external: a:1:{s:8:"facebook";a:1:{i:569696174;b:1;}}
  publicize_twitter_user: RussMatney
  _wpas_done_1917358: '1'
  _wpas_done_1917361: '1'
---
I watched a presentation last week: <a title="Uncle Bob Demands Professionalism" href="http://vimeo.com/56708193">Uncle Bob on Demanding Professionalism (in software development)</a> (80 minutes. It's worth it! Or here it is in <a title="30 minute uncle bob" href="http://vimeo.com/54025415">30 minutes, without the TDD intro</a>). It was my first exposure to <a title="Uncle Bob Martin" href="https://twitter.com/unclebobmartin">Uncle Bob Martin</a>, and it won't be my last. The video made me very excited about working with Moveline's Test-Driven Development process, and for the future of the software and web development industries. Uncle Bob knows what he's talking about, and it just seems like so much fun. (Nerd alert.)

The talk starts with a Test-Driven Development (TDD) of a Word-Wrap function. (Note the use of emacs as a text editor, and of course the unicorn that skips happily across the screen every time tests pass.) If you write code and you've never heard of TDD, here's my attempt at explaining it. TDD is a way of writing software in which you write 'tests' before the code itself. These 'tests' represent everything that your code needs to be able to do (Requirements).

In the video's unit-testing example, the Word-Wrap function needs to be able to handle a number of different kinds of input (some with spacing, some at various lengths). Our test-writer is here will use tests to put this Word-Wrap function to work. Because we are human and smart, we know what we want output should be for every input. Tests essentially give the function a known input and 'expect' a known output, then pass or fail based on that output.

<span style="font-style:inherit;line-height:1.625;">So, before even writing the Word-Wrap function, we write a test. When the test fails, you are free to write the function, </span>specifically<span style="font-style:inherit;line-height:1.625;"> to pass this test. When the test passes, you are golden, and that input case will always be handled as long as your tests are passing. Then you write the next test. Ideally, you write tests for every possible input case, providing you with a robust Word-Wrap function, with the bare-minimum of code needed for that function.</span>

Now imagine scaling this across an entire app. Every feature you add to the app is minimal, fully-tested, and ready to go live at any time. TDD takes a bit longer up-front, but saves HUGE amounts of time dealing with bugs down the road. And refactoring becomes completely stress-less. If your tests aren't passing, you broke something. If they are, you're fine. It's as easy as that.

TDD has a number of very serious advantages. Unless you are just prototyping, take the time to get it right.

Now, back to Uncle Bob. If you can't see the whole thing, Uncle Bob makes a few strong points. The Software industry is incredibly young. Right now, people don't have a great idea what developers do (aka, we're magic wizards playing with big powerful toys). Some of us are responsible, some of us are not. At some point in the future, some developer will accidentally cost humanity thousands of lives. This is a problem, but it's probably unavoidable. At the same time, it can be kept to a minimum if we are professional. When this big accident happens, the people will point at us, and the governments will want to certify and regulate us. But they don't know what we do the way we do. We need to figure out what our profession is/should be, before someone else does for us.

So, Uncle Bob, as our new CTO, has certain specific expectations. His list is very, very good. This post is getting long, so, in general: be honest, work hard. Don't be afraid to touch the code, or it will begin to rot. Maintain stable productivity. Continuously improve the code (don't be lazy or impatient!), don't start from scratch, make it better. Don't make a mess! It only slows you down! Don't be precise with deadlines, be accurate. If you're interested, watch one of his talks, they are all awesome.
