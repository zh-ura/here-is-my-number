# Here-is-my-number #

A tool to come up with easier word-based way to memorize a phone number.

Uses phone keyboard to map digits to letters.

Input phone number: 8002668228 

Output: 800contact.

## Pre-requisites
* Java 8 (get it at http://oracle.com)
* SBT (get it at http://www.scala-sbt.org)

## Build and Run ##

```sh
$ cd here-is-my-number
$ sbt
> jetty:start
> browse
```
If `browse` doesn't launch your browser, type in [http://localhost:8080/](http://localhost:8080/) in address bar.

### Stack
* [Scalatra](http://scalatra.org) with twirl for web
* Spark for mapping
