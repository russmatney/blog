---
title: "'What I am using AWS Lambda for'"
date: 2015-04-16
category: techsposure
tags: [Techsposure, tech]
---



###*TL;DR*:

I've got a few AWS Lambda functions in production,
composed with a handful of components I've grouped under the name
[Lambduh](https://github.com/lambduh/lambduh).

Shortlist of production lambda functions:

- [gif-to-mp4](https://github.com/russmatney/lambda-gif-to-mp4)
- [create-timelapse](https://github.com/russmatney/lambda-create-timelapse)
  - [file-to-png](https://github.com/russmatney/lambda-file-to-png)
  - [pngs-to-mp4](https://github.com/russmatney/lambda-pngs-to-mp4)
  - [mp4s-to-timelapse](https://github.com/russmatney/lambda-mp4s-to-timelapse)
  - [upload-to-vimeo](https://github.com/russmatney/lambda-upload-to-vimeo)

---

##Lambda is powerful

[Lambda](http://aws.amazon.com/lambda/) is a new tool in Amazon's suite of web services.

Each Lambda function runs on a fresh machine and can be handed any bit of data -
and you only pay for that machine's uptime, which at present is limited to 60 seconds.

Lambda recently added a slew of new features as it exited beta and offically launched.
My experience as of writing this is limited to interactions with S3 events
and invoking these functions via the AWS SDK.

[Separation of Concerns](http://en.wikipedia.org/wiki/Separation_of_concerns)
is huge for simplifying your codebase.
Lambda lets you define a function
without needing to maintain any of the infrastructure.
That's a big deal,
especially when it means any Lambda function you write is already scalable.

The achitecture resulting from using Lambda is functional in design -
for me Lambda (and microservices in general) feel like a functional language pattern
applied at the architectural level.

##What am I using it for?

Any task that you can isolate from your server
is a good candidate for a Lambda function.

In some recent consulting work (for [The Bosco](http://thebos.co/)),
I used Lambda to do some lazy file conversions
and to automate the creation of a timelapse video.

####Lazy, async file conversion

Let's say you have a .gif you'd like to save to S3.
Easy enough - just upload it.

Overtime, you may want that .gif in various other sizes and formats.
There are many ways to do this:

- Process the files locally, then upload all of them
- Run a task on your server that fetches, processes, reuploads

Not terrible options, but either way some work to do,
some bandwidth or server-load overhead,
and the continual knotting of server code or operational processes.
Wouldn't it be great if you could just upload one .gif and forget about it,
and all your processing would magically run itself?

Lambda functions can be invoked as a result of an S3 Put event,
which gives us an elegant solution:
Run a Lambda function to automatically process any .gif that hits S3.

[gif-to-mp4](https://github.com/russmatney/lambda-gif-to-mp4) is a Lambda function
that converts an uploaded gif into an mp4,
then uploads that mp4 to S3.
It takes about 5 seconds to run,
and we've seen zero fails.

One caveat: at this time,
an S3 bucket can only fire events to one Lambda function.

That means you either (1) need to do all your work reacting to that event in one function,
or (2) write a Lambda function that invokes other Lambda funcs based on the inital event.
This isn't too complicated, and I think it's a better strategy long-term.

For an example of how to do this,
the next section features a longer-running process (>60s)
that connects several Lambda functions.

###Create a timelapse

Another need for the Bosco - let's take an arbitrary slew of gifs
and string them into a mega timelapse with some music.
Ideally, we'd upload the finished product to Vimeo for all the world to see.

Processing this is not trivial – at a certain point,
too many gifs means the process is going to take more than 60 seconds
(we also need to download them all, and upload a ~25mb video to vimeo).

The solution I found is covered by these functions:

- [create-timelapse](https://github.com/russmatney/lambda-create-timelapse)
  - [file-to-png](https://github.com/russmatney/lambda-file-to-png)
  - [pngs-to-mp4](https://github.com/russmatney/lambda-pngs-to-mp4)
  - [mp4s-to-timelapse](https://github.com/russmatney/lambda-mp4s-to-timelapse)
  - [upload-to-vimeo](https://github.com/russmatney/lambda-upload-to-vimeo)

`create-timelapse` is something of an orchestrator;
it defines the interface for external consumers of this service,
and it manages the flow of the whole process,
invoking the other lambda functions when the time is right.

Accomplishing this is somewhat complicated because of Lambda's 60 second limitation –
`create-timelapse` gets it done by incrementing a timer
and invoking itself again after 45 seconds.
There is potential for more efficiency here,
perhaps with an external data store, or something like AWS's `waitFor()`.
For now, waiting a predetermined number of seconds is good enough and keeps things simple.

The rest of the functions do exactly as they are named:
converting files between various formats, applying watermarks, concatenating videos,
and uploading to Vimeo.

Some notes:

- We save a ton of time in `file-to-png`.
It's invoked for EVERY file that is passed,
which means as many machines as Lambda will give us at a time.
It's pretty awesome to see 100 gifs converted to 400 pngs in < 10 seconds.
- Another hurdle was building an mp4 out of 400+ images -
it just took the ffmpeg binary too long.
The solution here - run 50 pngs at a time in `pngs-to-mp4`,
then string those mp4s together in `mp4s-to-timelapse`.
- `upload-to-vimeo` was simple enough to earn it's own function,
but for some reason my upload requests would ECONNRESET out,
especially for larger videos.
Turns out, throwing a ton of memory at Lambda resolves this kind of thing
by giving you more CPU to work with.
I'm running this at 1024mb and 60s to ensure it works for larger videos.

#Some general lambda things

- `gif-to-mp4` has been very cheap to run (likely true for `create-timelapse`, data pending).
Paying only for uptime is very convenient in this case.
- Uploads/downloads between S3 and Lambda are very fast.
That's expected, but was a nice surprise –
I was running functions locally, and grew worried b/c things were slow to download/upload.
It was definitely an oversight on my part – working locally is dependent on your bandwidth and latency,
and things will run differently in the cloud.
- Lambda functions have very consistent results (just like functional programming!).
We've seen zero failures on the `gif-to-mp4`, and I'm expecting the same for `create-timelapse`
(which went into production this week).

#Lambduh

Between all these functions, there was plenty to DRY up.
[Lambduh](https://github.com/lambduh/lambduh) was my strategy for doing so.

Lambduh is a set of components for common tasks in Lambda functions.
The components so far:

- [lambduh-transform-s3-event](https://github.com/lambduh/lambduh-transform-s3-event) - Transforms S3 Event JSON into a flattened object with bucket and key
- [lambduh-validate](https://github.com/lambduh/lambduh-validate) - Validates fields according to your will
- [lambduh-execute](https://github.com/lambduh/lambduh-execute) - Executes any shell string or bash file
- [lambduh-get-s3-object](https://github.com/lambduh/lambduh-get-s3-object) - Download any file from S3 to a local filepath
- [lambduh-download-file](https://github.com/lambduh/lambduh-download-file) - Download any file based on URL
- [lambduh-put-s3-object](https://github.com/lambduh/lambduh-put-s3-object) - Upload any local file to S3
- [lambduh-list-s3-objects](https://github.com/lambduh/lambduh-list-s3-objects) - List S3 keys based on Bucket, prefix, and regex
- [lambduh-gulp](https://github.com/lambduh/lambduh-gulp) - Gulp tasks to make your lambda workflow all hunky-dory

The first thing - these functions are small!
It's likely you could just as easily build them into your function itself.
I built it because it's going to save me plenty of time across all these functions,
so maybe it'll save you some too.

I'll call out `lambda-execute` and `lambda-gulp`:

- `lambda-execute` makes it easy to run shell commands or a bash script,
which may help you get your bearings in Lambda's environment.
- `lambda-gulp` gives you `gulp zipload`,
which lets you define your Lambda function's configuration in a `lambda-config.js` file,
then easily create a new function or update your changes.

#So there's a bunch of hooplah

Lambda had quite a few quirks when I first dove in.
(See [this Quora answer](http://www.quora.com/What-are-your-experiences-running-on-AWS-Lambda-with-Node-JS-code/answer/Russ-Matney)
and [this Stack Overflow response](http://stackoverflow.com/questions/27708573/aws-lambda-making-video-thumbnails/29001078#29001078)
for specifics).

Many things have been resolved since then.
Though the Lambda/CloudWatch interface still struggles,
I'm not seeing any function caching issues,
and I no longer need to move or chmod any binaries before running them.

For the right situation, Lambda can be very useful!
AWS just added a couple new event sources,
and you can invoke these functions anywhere you have your AWS Credentials.

