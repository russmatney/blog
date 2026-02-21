---
title: "'Golang gotcha: `defer` zealot'"
date: 2016-07-09
category: techsposure
tags: [Techsposure, tech]
---



###*TL;DR*: Golang's `defer` and `sync.WaitGroup`s go hand in hand, but hit me with a conceptual gotcha this week.
Beware those `for{select}`s! Read on for details.

---

Got hit by a bug this week - I'm documenting it to inscribe it more strongly into my memory.

See if you can spot the issue:

```golang
// Worker should call f.pendingDownloads.Done() when each file is finished downloading + unzipping
func (f *FileManager) startDownloadWorker(downloadQueue <-chan downloadReq, abort chan struct{}) {
  for {
    select {
    case download, ok := <-downloadQueue: if !ok {
      // exit when no downloads left
      return
    }
    // call done when finished with file
    defer f.pendingDownloads.Done()
    var (
      baseName = filepath.Base(download.url)
      localDir = fmt.Sprintf("%s/%s", f.cfg.TempDir, download.subDir)
      localPath = fmt.Sprintf("%s/%s", localDir, baseName)
    )
    f.downloadFile(download.url, localPath)
    f.unzipFile(localPath, localDir)
    }
  }
}
```

That defer won't fire until all the downloads are done!
It waits until the end of the function,
which won't occur until the `for{}` exits.

Corrected:

```golang
// Worker should call f.pendingDownloads.Done() when each file is finished downloading + unzipping
func (f *FileManager) startDownloadWorker(downloadQueue <-chan downloadReq, abort chan struct{}) {
  for {
    select {
    case download, ok := <-downloadQueue: if !ok {
      // exit when no downloads left
      return
    }
    var (
      baseName = filepath.Base(download.url)
      localDir = fmt.Sprintf("%s/%s", f.cfg.TempDir, download.subDir)
      localPath = fmt.Sprintf("%s/%s", localDir, baseName)
    )
    f.downloadFile(download.url, localPath)
    f.unzipFile(localPath, localDir)
    // call done when finished with file
    f.pendingDownloads.Done()
    }
  }
}
```

It always seems obvious enough after the fact.
What dug me in was the happiness of `defer`.
I love being able to keep higher level knowledge closer to the top of code blocks,
rather than tucked away at the end -
calling `f.pendingDownloads.Done()` is definitely conceptually closer to the function's usage than it's implementation.
This one was a doozy for several minutes though.

`defer` is wonderful, but don't be a Zealot!
