# AGENTS.md

Guidance for AI coding agents working in this repository.

## Project Overview

Personal blog/devlog built with **Astro 5 + Starlight** (static site generator). Content is markdown files in `src/content/docs/`. No test suite — validation is done by building and previewing the site.

## Commands

```bash
bb dev          # or: npm run dev    — dev server at http://localhost:4321 (hot-reload)
bb build        # or: npm run build  — production build to dist/
bb preview      # or: npm run preview — preview prod build locally
bb --nrepl-server 1667              # optional: Babashka REPL for scripting tasks
```

There is no test runner. To validate changes: run `bb build` and check for TypeScript/Astro errors, then optionally `bb dev` to inspect visually.

## Code Style

- **TypeScript**: strict mode (`astro/tsconfigs/strict`); use explicit types in `.ts` and `src/content/config.ts`
- **Astro components**: frontmatter in `---` fences; use `Astro.props` with typed `Props` imports from Starlight
- **Imports**: named imports preferred; Starlight types come from `@astrojs/starlight/props` and `@astrojs/starlight/schema`
- **Babashka/Clojure** (`bb/` scripts): standard `ns` with `:require`, kebab-case names, `comment` blocks for REPL usage
- **Content frontmatter**: always include `title`; use `date: YYYY-MM-DD`, `category`, and `tags: [...]` when applicable
- **File naming**: content posts use `YYYY-MM-DD-slug.md`; undated pages use `slug.md`
- **Styles**: CSS custom properties from Starlight (`--sl-color-*`, `--sl-text-*`) for theming; global overrides in `src/styles/custom.css`
- **No linter config present**: follow existing patterns in components; avoid `console.log` left in production components

## Content Structure

- `src/content/docs/devlogs/` — dev logs
- `src/content/docs/posts/{100-worders,techsposure,getitwrite,groks,notes}/` — blog posts
- `src/content/docs/portfolio/` — portfolio pages
- `src/components/` — custom Astro component overrides (Footer, Head, ContentPanel)
- `public/` — static assets served as-is; `src/assets/` — images processed by Astro
