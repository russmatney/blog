russmatney.github.io
====================

A jekyll blog for tech and fiction.

# up'n'runnin'

## install

```
bundle install
```

## watch

```zsh
jekyll serve -w
#OR
bundle exec jekyll serve -w
```

## build and deploy

```zsh
jekyll build -d dst
gulp deploy
```

Requires config.js file:

```js
module.exports = {
  "aws": {
    "accessKeyId": "",
    "secretAccessKey": "",
    "params": {
      "Bucket": ""
    },
    "region": "us-east-1"
  }
};
```

# categories

## techsposure

Posts/guides/collections of resources covering mostly JavaScript (Angular, Node), but touching other languages and concepts as well (TDD, Uncle Bob, Blog Tech, etc)

## getitwrite

Writing, when paired with research and creativity, is an easy way to teach yourself anything and discover your true self. So why not?
