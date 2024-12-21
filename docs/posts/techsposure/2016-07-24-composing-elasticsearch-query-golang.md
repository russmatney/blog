---
categories:
  - Techsposure
date: '2016-07-24'
tags:
  - tech
title: Composing a Nested Elasticsearch Query in Golang
---

### _TL;DR_: A well-composed elasticsearch query is a beautiful thing, but it's easy to get lost on the way there. This post is one path. Stay clear-headed from the top down and you should be fine.

---

Elasticsearch is a fast database with a powerful query language.

Golang is a fine language to build an app with, and with a little work,
you can use it to present a clean search abstraction to the rest of your app.

### Requirements

- I'm running Elasticsearch (v2.3) via Docker Native on my mac
- I'm running `go1.7beta1`
- The source code for this whole post is available [here](https://github.com/russmatney/nested-query-example-elasticsearch-golang)

# The dataset: Pokemon trainers with nested Poke-docs

Pokemon is topical enough to give us a nested data structure.
We're going to be working with a `trainer` type that has a nested property
`pokemon`, which is a `type: nested` array on the doc.

The mapping:

```yaml
mappings:
  trainer:
    properties:
      name:
        type: string
      hometown:
        type: string
      pokemon:
        type: nested
        properties:
          name:
            type: string
          level:
            type: long
```

## `type: nested`?

[Nested documents in elasticsearch](https://www.elastic.co/guide/en/elasticsearch/reference/current/nested.html)
maintain the associations you'd expect.

Take this trainer:

```json
{
  "name": "Ash",
  "pokemon": [
    { "name": "Pikachu", "level": 7 },
    { "name": "Charizard", "level": 45 }
  ]
}
```

If our mapping did not mark the type of field `pokemon` as `nested`,
elasticsearch would have flattend these pokemon onto the trainer object.

```js
// Pseudo code: Elasticsearch internals (via Lucene)
{
  "name": "ash",
  "pokemon.name": ["pikachu", "charizard"]
  "pokemon.level": [7, 45]
}
```

Searches for a level 45 pikachu would return this trainer,
when in fact he has no such thing!

Using `type: nested` tells elasticsearch to store each of these as separate documents.
This lets us to search for individual pokemon on trainers,
but also requires us to build a nested query - elasticsearch does not do that
work for us.

> Elasticsearch doesn't store nested documents on the root doc at all,
> which can be a performance improvement for your queries that _don't_ touch the nested object -
> especially if those nested pokemon add significant size to the document.

# Anyone have a Level 19 Magikarp?

Our goal with this query builder will be an abstraction that we can use to search for trainers.
Specifically, we want to find a trainer with a pokemon matching our search criteria.

Something like:

```golang
// Golang!
trainers, err := Storage.FetchTrainers(&TrainerSearchOpts{
  Pokemon: "Magikarp",
  Level: 19,
})
```

## Basics first

A basic FetchTrainers via `olivere/elastic` might look like:

```golang
// FetchTrainers queries trainers with the passed options
func (s *Store) FetchTrainers(opts TrainerSearchOpts) ([]*trainers.Trainer, error) {
	query := elastic.NewMatchAllQuery()

	res, err := s.es.Search(s.trainerIndex).Type(s.trainerType).Query(query).Do()
	if err != nil {
		return nil, err
	}

	var trns []*trainers.Trainer
	for _, iT := range res.Each(reflect.TypeOf(&trainers.Trainer{})) {
		trns = append(trns, iT.(*trainers.Trainer))
	}
	return trns, nil
}
```

We're going to refactor this into a nested query that uses a filter to limit the
returned trainers to those with a pokemon matching the search.

## Learn that Bool Query

The [Bool Query](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-bool-query.html)
is the key to composability in elasticsearch queries.
You can read more in the docs, but the gist is that you can attach arrays
of subqueries to a single Bool Query, and it is treated as a single query
wherever you drop it in.

At our top-level query, we're going to create a Bool query and then attach a Filter.
If no other queries are attached, Bool Queries default to using a `"match_all"`,
so attaching a Filter will limit the results to everything in the index that
gets through our filter.

```golang
// switch to a Bool query
query := elastic.NewBoolQuery()
// set some filters
query = query.Filter(buildFilters(opts)...)
```

## Where that nested magic happens

We need to implement the `buildFilters` function used above.
It should return a slice of filters relevant to our query -
in this case, we want to limit the results to our search criteria.

```golang
func buildFilters(opts TrainerSearchOpts) []elastic.Query {
	return []elastic.Query{
		elastic.NewNestedQuery(
			"pokemon",
			elastic.NewMatchQuery("pokemon.name", opts.Pokemon),
		),
	}
}
```

We use `NewNestedQuery(path string, query elastic.Query)` to get a nested query,
then set a match query for a pokemon's name.

This works fine for filtering on a single field, and leaves it easy for us to
add more filters of our choosing (perhaps on a trainer's badges or items).
However, the moment we want to search for a more specific nested pokemon, this will
start to get a little messy.

> Notable here: if you were to add a second nested filter to `buildFilters()`'s
> slice that matched on a pokemon's level, the queries would not be applied to the same pokemon.
> Queries that you want to apply to the same nested object must live on the same nested query.

Let's go ahead and refactor it:

```golang
func buildFilters(opts TrainerSearchOpts) []elastic.Query {
	return []elastic.Query{
		elastic.NewNestedQuery(
			"pokemon",
			buildPokemonFilter(opts)
		),
	}
}

func buildPokemonFilter(opts TrainerSearchOpts) elastic.Query {
	return elastic.NewBoolQuery().Filter(
		elastic.NewMatchQuery("pokemon.name", opts.Pokemon),
		elastic.NewMatchQuery("pokemon.level", opts.Level),
	)
}
```

Here we're using the composability of the Bool Query again - we want both our
Match queries to apply as a single boolean signal to the nested Query above.

# Off to the Pokemon League!

This could be taken much farther, and the design should be shaped by the API you
want to expose. I don't have a strong search story yet here, but if you want to
see this taken a few steps farther, leave a comment with your use-case, I'm
happy to keep extending the metaphor.

I hope this helps unlock your use of Elasticsearch in Golang! Good luck catching
them all!

[Full source code](https://github.com/russmatney/nested-query-example-elasticsearch-golang).
