import { defineCollection, z } from 'astro:content';
import { docsSchema } from '@astrojs/starlight/schema';

export const collections = {
  docs: defineCollection({
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
