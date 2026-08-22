import { defineConfig } from 'astro/config';
import starlight from '@astrojs/starlight';
import sitemap from '@astrojs/sitemap';

// https://astro.build/config
export default defineConfig({
  site: 'https://russmatney.com',
  integrations: [
    sitemap(),
    starlight({
      title: 'Danger Russ Blog',
      description: 'A nerdy and meandering mind garden',
      logo: {
        src: './src/assets/portrait-nobg-2x.png',
      },
      social: [
        {
          label: 'GitHub',
          icon: 'github',
          href: 'https://github.com/russmatney/blog',
        },
      ],
      customCss: [
        './src/styles/custom.css',
      ],
      sidebar: [
        {
          label: 'Home',
          link: '/',
        },
        {
          label: 'About',
          link: '/about',
        },
        {
          label: 'Neighborhood',
          link: '/neighborhood',
        },
        {
          label: 'Dev Logs',
          items: [{autogenerate: { directory: 'devlogs' }}]
        },
        {
          label: 'Portfolio',
          items: [{autogenerate: { directory: 'portfolio' }}]
        },
        {
          label: 'Posts',
          items: [
            {
              label: 'Hundos',
              items: [{autogenerate: { directory: 'posts/100-worders' }}],
              collapsed: true,
            },
            {
              label: 'Techsposure',
              items: [{autogenerate: { directory: 'posts/techsposure' },}],
              collapsed: true,
            },
            {
              label: 'Get It Write',
              items: [{autogenerate: { directory: 'posts/getitwrite' },}],
              collapsed: true,
            },
            {
              label: 'Groks',
              items: [{autogenerate: { directory: 'posts/groks' },}],
              collapsed: true,
            },
            {
              label: 'Notes',
              items: [{autogenerate: { directory: 'posts/notes' },}],
              collapsed: true,
            },
          ],
        },
      ],
      components: {
        Footer: './src/components/Footer.astro',
        Head: './src/components/Head.astro',
        ContentPanel: './src/components/ContentPanel.astro',
      },
    }),
  ],
  markdown: {
    shikiConfig: {
      theme: 'one-dark-pro',
      langs: ['gdscript', 'haskell', 'clojure', 'bash', 'javascript', 'typescript', 'go'],
    },
  },
});
