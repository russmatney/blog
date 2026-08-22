import { defineCollection } from 'astro:content';
import { docsLoader } from '@astrojs/starlight/loaders';
import { docsSchema } from '@astrojs/starlight/schema';
import { z } from 'astro/zod';

export const collections = {
  docs: defineCollection({
    loader: docsLoader(),
    schema: docsSchema({
      extend: z.object({
        // Extended fields for blog posts
        category: z.string().optional(),
        tags: z.array(z.string()).optional(),
        date: z.coerce.date().optional(),
      }),
    }),
  }),
};
