# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a personal blog/devlog site built with Docsify (static site generator) and Babashka (Clojure scripting). The blog content is stored as markdown files in `docs/`, while Clojure scripts in `bb/` generate indexes, sidebars, and manage content organization.

Key characteristics:
- Docsify serves markdown from `docs/` as a static website
- Content is organized into: devlogs, portfolio, posts (with subcategories: 100-worders/hundos, techsposure, getitwrite, groks, notes)
- Babashka scripts automatically generate READMEs and _sidebar.md files for each section
- Uses Nix/direnv for environment setup (via lorri or fallback to `use nix`)

## Development Commands

### Building/Regenerating Content

```bash
bb build
```
Regenerates all index pages (README.md) and sidebars (_sidebar.md) for all sections. This runs `blog.tasks/regen-all-pages` which calls `blog.pages/write-indexes-and-sidebars`.

### Local Development Server

```bash
bb serve
# or
bb watch  # same as serve, but suggests running nrepl server
```
Starts docsify dev server to preview the site locally. The `watch` task is intended for development with REPL.

### REPL Development

```bash
bb --nrepl-server 1667
```
Start an nREPL server on port 1667 for interactive development.

## Architecture

### Content Organization

All site content lives in `docs/`:
- `docs/homepage.md` - Landing page
- `docs/about.md` - About page
- `docs/devlogs/` - Development logs
- `docs/portfolio/` - Portfolio items
- `docs/posts/` - Blog posts with subdirectories:
  - `100-worders/` - Short stories/poems (aka "hundos")
  - `techsposure/` - Tech posts
  - `getitwrite/` - Writing-focused posts
  - `groks/` - Learning notes
  - `notes/` - Garden notes (work in progress)
- `docs/html/` - Raw reveal.js presentations

### Code Structure (bb/ directory)

The `bb/` directory contains Clojure/Babashka scripts:

- **blog/tasks.clj** - Main task entry points (`regen-all-pages`)
- **blog/pages.clj** - Page generation logic, defines page definitions for each section
- **blog/generate.clj** - Low-level file writing utilities
- **blog/post.clj** - Post parsing and aggregation (reads markdown files, extracts metadata from filenames)
- **blog/markdown.clj** - Markdown link generation
- **blog/dates.clj** - Date parsing from filenames (format: YYYY-MM-DD-title.md)
- **blog/config.clj** - Directory paths configuration
- **blog/garden.clj** - Garden notes integration (from external org files)

### Page Generation System

Pages are defined as maps with keys like:
- `:generate` - Function to call (usually `write-sidebar` or `write-index`)
- `:directory` - Target directory
- `:title` - Section title
- `:->text` - Function to format each post as text/link
- `:->posts` - Function to fetch posts (defaults to posts in that directory)
- `:preamble` - Custom intro text for the page
- `:skip-group-by` - Whether to skip date-based grouping

Generated files include a preamble comment indicating they're auto-generated.

### Post Metadata Extraction

Posts are parsed from filenames using this pattern:
- `YYYY-MM-DD-title.md` - Extracts date and title
- Non-dated files use title from filename and last-modified timestamp
- Tags are inferred from parent directory name

### Docsify Configuration

- Main config: `docs/index.html`
- Uses `theme-simple-dark` for dark mode, `theme-simple` for light mode
- Custom plugins: gifcontrol, breadcrumb, search, copy-code, pagination
- Syntax highlighting for GDScript and Haskell
- Homepage is `homepage.md` (not README.md)

## Workflow Notes

1. **Adding new blog posts**: Create markdown file in appropriate subdirectory under `docs/posts/` or `docs/devlogs/`, then run `bb build` to regenerate indexes.

2. **Modifying generated pages**: Don't edit README.md or _sidebar.md files directly in category directories - they're auto-generated. Instead, modify the page definitions in `bb/blog/pages.clj` and run `bb build`.

3. **Garden notes integration**: This blog can pull content from external org-mode files (configured to read from `~/todo/garden/`), though this feature is currently commented out in tasks.

4. **Testing locally**: Run `bb serve` and visit the local server to preview changes before committing.
