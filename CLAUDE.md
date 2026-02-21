# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a personal blog/devlog site built with **Astro/Starlight** (static site generator) with optional Babashka (Clojure scripting) for content management utilities.

Key characteristics:
- Astro/Starlight generates a static website from markdown content
- Content is organized into: devlogs, portfolio, posts (with subcategories: 100-worders/hundos, techsposure, getitwrite, groks, notes)
- Starlight auto-generates navigation sidebars from directory structure
- All content has frontmatter (title, date, category, tags)
- Uses Nix/direnv for environment setup (via lorri or fallback to `use nix`)

## Development Commands

### Local Development Server

```bash
bb dev
# or
npm run dev
```
Starts Astro dev server on `http://localhost:4321`. Hot-reloads on file changes.

### Building for Production

```bash
bb build
# or
npm run build
```
Builds the static site to `dist/` directory. Generates ~250 HTML pages with Pagefind search index.

### Preview Production Build

```bash
bb preview
# or
npm run preview
```
Preview the production build locally before deploying.

### REPL Development (Optional)

```bash
bb --nrepl-server 1667
```
Start an nREPL server on port 1667 for interactive Babashka development.

## Architecture

### Content Organization

All site content lives in `src/content/docs/`:
- `src/content/docs/index.mdx` - Homepage (splash template)
- `src/content/docs/about.md` - About page
- `src/content/docs/devlogs/` - Development logs
- `src/content/docs/portfolio/` - Portfolio items (games, open source, videos, resume)
- `src/content/docs/posts/` - Blog posts with subdirectories:
  - `100-worders/` - Short stories/poems (aka "hundos")
  - `techsposure/` - Tech posts
  - `getitwrite/` - Writing-focused posts
  - `groks/` - Learning notes
  - `notes/` - Garden notes (work in progress)

Static assets:
- `src/assets/` - Images and media files
- `public/html/` - Reveal.js presentations (served as static files)
- `public/CNAME` - Custom domain configuration

### Frontmatter Structure

All content files use YAML frontmatter:

```yaml
---
title: "Post Title"
date: 2025-01-08
category: devlogs
tags: [devlog, godot]
---
```

Required fields:
- `title` - Page title (required by Starlight)

Optional fields:
- `date` - Publication date (YYYY-MM-DD format)
- `category` - Content category (matches directory structure)
- `tags` - Array of tags for filtering/organization
- `template` - Page template (e.g., `splash` for homepage)

### Astro Configuration

**Main config:** `astro.config.mjs`

Key settings:
- Site URL: `https://russmatney.com`
- Theme: Custom purple accent (#dc703f), 18px base font
- Sidebar: Auto-generated from directory structure with collapsed categories
- Custom components: Footer component (`src/components/Footer.astro`)
- Syntax highlighting: GDScript, Haskell, Clojure, Go, JavaScript, TypeScript
- Search: Pagefind (auto-generated, indexes ~245 pages)

**Content Collections:** `src/content/config.ts`

Extends Starlight's schema with custom fields (category, tags, date).

### Babashka Scripts (Optional Utilities)

The `bb/` directory contains Clojure/Babashka migration utilities:

- **blog/migrate.clj** - Frontmatter normalization (used during migration)
- **blog/copy_to_astro.clj** - Content copying utilities (used during migration)
- **blog/config.clj** - Directory paths configuration
- **blog/dates.clj** - Date parsing utilities
- **blog/post.clj** - Post metadata extraction

These scripts are primarily for migration and batch content operations. Normal content editing doesn't require them.

### Post Filename Convention

Posts follow this naming pattern:
- `YYYY-MM-DD-title.md` - Date-prefixed posts (most common)
- `title.md` - Undated pages (portfolio, about, etc.)

The date is extracted from the filename during migration and stored in frontmatter. Starlight uses the frontmatter `date` field, not the filename.

### Deployment

**GitHub Actions:** `.github/workflows/deploy.yml`

Automatic deployment on push to `main`:
1. Builds site with `npm run build`
2. Uploads to GitHub Pages
3. Serves at `https://russmatney.com`

Custom domain is preserved via `public/CNAME`.

## Workflow Notes

1. **Adding new blog posts**:
   - Create markdown file in appropriate subdirectory under `src/content/docs/posts/` or `src/content/docs/devlogs/`
   - Include frontmatter with at least a `title` field
   - Starlight auto-generates navigation, no manual index needed
   - Preview with `bb dev`

2. **Modifying navigation**:
   - Starlight auto-generates sidebars from directory structure
   - To customize, edit the `sidebar` config in `astro.config.mjs`
   - No manual README.md or _sidebar.md files needed (these are legacy from Docsify)

3. **Styling changes**:
   - Global styles: `src/styles/custom.css`
   - Component overrides: `src/components/`
   - Theme colors: CSS variables in `custom.css`

4. **Testing locally**:
   - Run `bb dev` or `npm run dev`
   - Visit `http://localhost:4321`
   - Changes hot-reload automatically

5. **Garden notes integration**:
   - Legacy Babashka scripts can pull content from external org-mode files (`~/todo/garden/`)
   - Currently not active, but scripts remain available for future use

## Key Differences from Docsify

- ✅ **Better SEO**: Pre-rendered static HTML (not client-side JavaScript)
- ✅ **Superior search**: Pagefind indexes all content
- ✅ **Auto-generated navigation**: No manual sidebar maintenance
- ✅ **Type-safe frontmatter**: TypeScript validation via content collections
- ✅ **Modern tooling**: Vite, TypeScript, component-based architecture
- ⚠️ **Build step required**: Must run `npm run build` (but dev server is fast)

## Migration Notes

The site was migrated from Docsify to Astro/Starlight in February 2025. Key migration steps:
1. Created frontmatter normalization script to standardize metadata
2. Copied content from `docs/` to `src/content/docs/`
3. Fixed date formats and invalid YAML
4. Removed auto-generated README/_sidebar files (no longer needed)
5. Created GitHub Actions deployment workflow

Old Docsify files remain in `docs/` for reference but are not used by the build system.
