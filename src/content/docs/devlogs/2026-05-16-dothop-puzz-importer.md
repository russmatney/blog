---
title: "Dot Hop: Plain-Text Levels with a Godot Import Plugin"
date: 2026-05-16
category: devlogs
tags: [devlog, dothop]
---

> This is a draft/outline! Plz don't read yet. Coming soon!

# Devlog: Dot Hop's .puzz Importer

Heya! Today I'm writing up one of my favorite recent additions to Dot Hop: a
plain-text level format (`.puzz`) backed by a custom Godot import plugin.

:::tip[TLDR]
Dot Hop's puzzles are defined as plain-text ASCII grids in `.puzz` files. A
custom Godot `EditorImportPlugin` reads those files at import time and produces
first-class `PuzzleSetData` resources — no runtime parsing, no in-betweener
classes, just load the resource and go.
:::

## Links

- [Dot Hop on GitHub](https://github.com/russmatney/dothop) — the full source
- <!-- TODO: link directly to the import plugin script in the repo, e.g. addons/puzz_importer/puzz_import_plugin.gd -->
- <!-- TODO: link to Godot's EditorImportPlugin docs if helpful -->

---

## The `.puzz` File Format

`.puzz` files are plain text — one line per row of the puzzle grid. Each
character maps to a cell type:

| Character | Meaning |
|---|---|
| `.` | Empty cell |
| `o` | Dot (collectable) |
| `x` | Player start |
| `t` | Target / goal |

A minimal puzzle looks like this:

```
.......
.xooot.
.......
```

<!-- TODO: expand character key if there are more cell types (walls, etc.) -->
<!-- TODO: confirm whether multiple puzzles live in one .puzz file or one per file -->

Here's what a real `.puzz` file looks like in a text editor:

<img src="/images/dothop/raw_puzz_file.png" />

The format is intentionally readable — you can author or tweak puzzles by hand,
and diffing changes in version control is trivial.

---

## The Godot Import Plugin

Godot's `EditorImportPlugin` system lets you register a custom file extension
and teach the editor how to import it. Once registered, any `.puzz` file in the
project is automatically imported whenever it changes — exactly like a PNG or
WAV.

<!-- TODO: paste or link the plugin registration boilerplate:
     - get_importer_name()
     - get_recognized_extensions() → ["puzz"]
     - get_save_extension() → "res"
     - get_resource_type() → "Resource"
     - import() → parse the file and return a PuzzleSetData
-->

<!-- TODO: walk through the import() method step by step:
     1. Open the .puzz file
     2. Split into lines
     3. Build the PuzzleSetData resource
     4. Save with ResourceSaver
-->

After import, the result appears in Godot's FileSystem dock as a proper
`PuzzleSetData` resource — inspectable, preloadable, and type-safe:

<img src="/images/dothop/puzzle_set_data_resource.png" />

---

## GDScript Wrappers & Helpers

The import plugin produces a `PuzzleSetData` resource. A few supporting scripts
handle the rest:

<!-- TODO: describe PuzzleSetData — what fields does it expose?
     e.g. puzzle_lines: Array[Array], dot_count: int, etc. -->

<!-- TODO: describe any parser helper (PuzzParser? PuzzReader?) that the import()
     method delegates to -->

<!-- TODO: note the removal of GameDef — what was it, and why could it go away
     once .puzz files became first-class resources? -->

One of the nicest wins here was deleting the `GameDef` class entirely. It had
been an awkward in-betweener — a bag of parsed data that nothing else quite
knew how to treat. Once `.puzz` files became proper Godot resources, `preload()`
and `load()` just work, and `GameDef` had nothing left to do.

---

## In-Engine

With `PuzzleSetData` as a real resource, the puzzle browser tool came together
quickly. Here's a happy accident from that work — what happens when you forget
to remove the old puzzle node before adding a new one:

<img src="/images/dothop/puzzle_stacking.gif" />

<!-- TODO: add any additional in-editor screenshots showing the import result,
     the inspector view of a PuzzleSetData, or the FileSystem dock -->

---

## Stretch Goal: A Minimal Reproducible Example

A standalone Godot project demonstrating just the import plugin would be a clean
way to share this pattern. For now, the full implementation lives in the Dot Hop
repo — it's open source and the relevant addon should be easy to find:

- [github.com/russmatney/dothop](https://github.com/russmatney/dothop)

<!-- TODO: once the addon is extracted/polished, link to it as a standalone repo
     or Godot Asset Library entry -->

---

Thanks for reading! If you're building something with Godot and want to talk
plain-text level formats or import plugins, find me online — happy to chat.

Happy trails, game devs!
