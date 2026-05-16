import rss from "@astrojs/rss";
import { getCollection } from "astro:content";
import type { APIContext } from "astro";

export async function GET(context: APIContext) {
  const docs = await getCollection("docs");

  const feedItems = docs
    .filter((entry) => {
      const isContent =
        entry.id.startsWith("devlogs/") || entry.id.startsWith("posts/");
      const hasDate = entry.data.date != null;
      // Exclude index pages
      const isIndex = entry.id.endsWith("/index") || entry.id === "index";
      return isContent && hasDate && !isIndex;
    })
    .sort(
      (a, b) =>
        new Date(b.data.date!).getTime() - new Date(a.data.date!).getTime(),
    )
    .map((entry) => ({
      title: entry.data.title,
      pubDate: new Date(entry.data.date!),
      description: entry.data.description ?? "",
      link: `/${entry.id.replace(/\.mdx?$/, "")}/`,
    }));

  return rss({
    title: "Russell Matney",
    description: "Devlogs, blog posts, and notes from Russell Matney",
    site: context.site!,
    items: feedItems,
    customData: "<language>en-us</language>",
  });
}
