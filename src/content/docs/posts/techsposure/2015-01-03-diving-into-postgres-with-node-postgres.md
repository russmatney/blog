---
title: "'Diving into PostgreSQL with node-postgres'"
date: 2015-01-03
category: techsposure
tags: [Techsposure, tech]
---



###*TL;DR*:

A quick brain-dump following my first experience with running PostgreSQL.
Related repo is [on Github](https://github.com/russmatney/postgres-playground).

---

##Time for Postgres

I'm working on a new project that is begging for a DB smarter than MongoDB.
I've never worked in SQL land,
but know that I wanted `joins`
and SQL experience,
and I've heard good things about PostgreSQL.

So what's the fuss all about?
I'm not sure yet.
This post is focused on how I got PostgreSQL running for the first time on my
machine.

Caveat: My knowledge of PostgreSQL at the time of writing this is more or less
limited to the contents of this post.

##Brew

I use [homebrew](http://brew.sh/) to manage the majority of my dev tools,
so PostgreSQL is no different.

    brew install postgresql

##Get it running

Once installed, a number of guides I found seemed to want me to use
`initdb` to create a new database.
Don't worry about this for now, we'll install one later.

[Optional] PostgreSQL can be started automatically on login via launchctl or a gem called
lunchy ([some launchctrl
context](http://robots.thoughtbot.com/starting-and-stopping-background-services-with-homebrew)).
I opted to not do this - I prefer to keep things manual,
especially when working with a new piece of tech.
One reason for this: I like to see the database logs as they happen,
so I don't get lost in a mess of background processes when something doesn't
seem to be working.

I added the following aliases to my [zschrc](https://github.com/russmatney/dotfiles/commit/68645933ff2cc591d577aaed52af643998be1128):

    # Postgres aliases
    alias pg_start='pg_ctl -D /usr/local/var/postgres -l /usr/local/var/postgres/server.log start'
    alias pg_stop='pg_ctl -D /usr/local/var/postgres stop -s -m fast'

But again, to start, I'm going to be running PostgreSQL manually to see the live
logging in my terminal window:

    pg_ctl -D /usr/local/var/postgres -l /usr/local/var/postgres/server.log start

If it works, you'll see something like this:

    LOG:  database system was shut down at 2015-01-03 16:36:44 EST
    LOG:  database system is ready to accept connections
    LOG:  autovacuum launcher started

Hopefully this command works for you - if not, debug a bit, run some `--help`
commands, or drop a line over [Twitter](https://twitter.com/russmatney) and I can help troubleshoot.

##node-postgres

[node-postgres](https://github.com/brianc/node-postgres) is a node client for
postgres. You can install it with `npm install pg`.

The maintainers provide an [example node
app](https://github.com/brianc/node-postgres/wiki/Example)
that I used to jumpstart my Postgres usage.
I grabbed and threw this into a
[repo](https://github.com/russmatney/postgres-playground).
You can run the script from the project's root with `node script-name.js`,
in my case, `node node-postgres-example.js`.

Take a read through the script.
For our getting-Postgres-running purposes,
we're mostly concerned with the conString being used.

    var conString = "postgres://postgres:1234@localhost/postgres";

The triple use of "postgres" makes this a fun little puzzle, eh?

`postgres://` is specifying the postgres protocol
(the same way mongodb commands start with `mongodb://`).
This should never change while you're using postgres.

`postgres:1234` specifies a username and password.
We'll create this user w/ password shortly.

`@localhost/postgres` specifies the host and then the name of the database you
wish to connect to.

Recap:

    var conString = "<protocol>://<username>:<password>@<host>/<databaseName>";

##Run that script!

If you haven't already, get Postgres running and run that script via the `node`
command.
If you go to http://localhost:3001 in your browser, you'll ping the server,
and the script will try to connect to the db and insert an entry into a Table
called 'visits'.

At this point, this will fail for a few reasons, which we can walk through next.

##Run script, Ping browser, Read Error, Debug, Repeat

###FATAL:  role "postgres" does not exist

The first error I saw was a double. The script failed with:

    TypeError: Cannot read property 'query' of null

and Postgres reported:

    FATAL:  role "postgres" does not exist

The node script is not well developed for non-postgres users -
drop in plenty of console.logs to help yourself debug faster.

The important bit here is the Postgres error,
which tells us we have no `postgres` user in the DB.

From here I went down a winding path.
It seems you can create users a few ways,
each with varying levels of security.
I went for the easiest way - DO NOT assume this is a solid way to create
postgres roles in a production database.

The `psql` command
([psql docs](http://www.postgresql.org/docs/current/interactive/app-psql.html))
drops you into a PostgreSQL repl within a database of your
choosing. Here we're going to create a user account database, use `psql` to hop into it,
then create a new user and database to play with.

    createdb russellmatney
    //or your own username - this name matched the
    //`database [x] does not exist` err that shows if you run `psql` on it's own
    psql
    CREATE USER postgres_admin WITH PASSWORD '1234';
    CREATE DATABASE postgres_db OWNER postgres_admin;

It's odd to create a db in your own user name.
At this point I believe this is a user-account database,
which I'm taking to mean a database for storing users
and giving us a place in our db cluster to work from.

Update your script's conString with the new info:

    var conString = "postgres://postgres_admin:1234@localhost/postgres_db";

Run it, ping it, and check your db/script output to see where we are.

###ERROR:  relation "visit" does not exist at character 13

The next error I saw looked like this:

    ERROR:  relation "visit" does not exist at character 13

Our "visit" table does not exist!
Let's psql into the database and create it.

    psql postgres_db
    CREATE TABLE visit (date date);

Don't forget that semicolon!

Run + ping + check again.

###ERROR:  permission denied for relation visit

The last thing I ran into here:

    ERROR:  permission denied for relation visit

It seems our `postgres_admin` role does not have the adequate permissions to
access the table we've created,
despite being the owner of the database.

Useful commands within psql that helped me resolve this bug:

- `\du` - lists the users and their priviledges
- `\list` - lists the databases in the cluster and their owners
- `\dt` - list the tables in the current database
- `\q` - exit the repl

Spot the issue yet?

If you log into the postgres_db database and run `\dt`,
you can see the `visit` table's owner is not postgres_admin.
We did not specify an owner when we created the table,
so it defaulted to our logged in user (for me, that's `russellmatney`).

A quick fix to get it working is to update `conString` to use your own username:

    var conString = "postgres://russellmatney:1234@localhost/postgres_db";

But that's not ideal. I suspect we could also `DROP` (SQL for delete) the table and
recreate it using `WITH OWNER postgres_admin`.

But let's touch on something else: Priviledges and the GRANT command.
I found [some reading
here](http://www.postgresql.org/docs/8.1/static/privileges.html)
and it's just what we need:

    postgres_db=# GRANT ALL ON visit TO postgres_admin;

Refresh your browser, and you should be a happy camper!
Watch your table grow and your visits tick up each time you refresh.

##Postgres Post Game

So we're up and running!
Next steps are wide open:

- Explore PostgreSQL's super powers
- Toy with a handful of node clients
- WTF is a join? Amirite?

##Some Resources:

[PostgreSQL 9.4 docs](http://www.postgresql.org/docs/9.4/static/index.html):

  - I should probably have started here,
  but I never seem to have the patience to read the docs before trying it once.

[How To Create, Remove, & Manage Tables in PostgreSQL on a Cloud Server](https://www.digitalocean.com/community/tutorials/how-to-create-remove-manage-tables-in-postgresql-on-a-cloud-server):

  - I didn't get into the rootuser `su` stuff, but it's definitely related to
  	the error in Table ownership that we hit
  - The rest of this article was helpful for the psql and SQL commands

##Good luck!

Reach out if you have trouble debugging any issues discussed,
or if something could be better explained! [@russmatney on
Twitter](https://twitter.com/russmatney).
