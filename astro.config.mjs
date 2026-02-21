import { defineConfig } from 'astro/config';
import starlight from '@astrojs/starlight';

// https://astro.build/config
export default defineConfig({
  site: 'https://russmatney.com',
  integrations: [
    starlight({
      title: 'Danger Russ Blog',
      description: 'A nerdy and meandering mind garden',
      logo: {
        src: './src/assets/portrait-nobg-2x.png',
      },
      social: {
        github: 'https://github.com/russmatney/blog',
      },
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
          label: 'Dev Logs',
          autogenerate: { directory: 'devlogs' },
        },
        {
          label: 'Portfolio',
          autogenerate: { directory: 'portfolio' },
        },
        {
          label: 'Posts',
          items: [
            {
              label: 'Hundos',
              autogenerate: { directory: 'posts/100-worders' },
              collapsed: true,
            },
            {
              label: 'Techsposure',
              autogenerate: { directory: 'posts/techsposure' },
              collapsed: true,
            },
            {
              label: 'Get It Write',
              autogenerate: { directory: 'posts/getitwrite' },
              collapsed: true,
            },
            {
              label: 'Groks',
              autogenerate: { directory: 'posts/groks' },
              collapsed: true,
            },
            {
              label: 'Notes',
              autogenerate: { directory: 'posts/notes' },
              collapsed: true,
            },
          ],
        },
      ],
      components: {
        Footer: './src/components/Footer.astro',
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
