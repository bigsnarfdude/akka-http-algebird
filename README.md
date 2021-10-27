# Project Rehydrate
## Akka HTTP Algebird example

This project demonstrates the [Akka HTTP](http://doc.akka.io/docs/akka-stream-and-http-experimental/current/scala.html) library. Simple Scala REST service wrapping Twitter Algebird HLL library to make an analytics query engine to provide Distinct Counts for millions of items using [HyperLogLog Algorithm](http://algo.inria.fr/flajolet/Publications/FlFuGaMe07.pdf).

References 2021:

https://en.wikipedia.org/wiki/Category:Probabilistic_data_structures

https://twitter.github.io/algebird/datatypes/approx/hyperloglog.html

http://twitter.github.io/algebird/datatypes/approx/exponential_histogram.html

https://en.wikipedia.org/wiki/Monad_(functional_programming)

http://antirez.com/news/75

https://github.com/CamDavidsonPilon/tdigest

https://github.com/CamDavidsonPilon/lifetimes

https://github.com/CamDavidsonPilon/lifelines

https://webd.is/

https://en.wikipedia.org/wiki/Slowly_changing_dimension

https://github.com/twitter/algebird/blob/develop/algebird-core/src/main/scala/com/twitter/algebird/MapAlgebra.scala#L196

https://gist.github.com/bigsnarfdude/ff8fbbfd1c038a97a3b0

https://github.com/isarn/isarn-sketches-spark

https://github.com/isarn/isarn-sketches-algebird-api


You will need Redis. This Akka HTTP project persists data to Redis.

HLLs are stored as Base64 encoded strings using Twitter Chill (Kryo under the covers) for every minute. 

Key: `loginService-2015-08-21T04:29:00.000` 
HLLValue: `encodedHLLString`
Value: `18394`


## Usage

Start services with sbt:

```
$ sbt run
```


The service provides 2 REST endpoints:

With the service up, you can start sending HTTP requests to the different endpoints:

#### Add UUID value to the loginService and return the distinct users seen for the interval:
```
$curl -i \
  -H "Accept: application/json" \
  -H "X-HTTP-Method-Override: PUT" \
  -X POST -d '{"servername": "loginService", "value": "4cd4f31f-3de2-4428-b457-04b75396214e"}' \
  http://localhost:9000/addHLL
{
  "key": "loginService_2015-10-21T18:47:00.000",
  "estimatedSize": 99797.80031724533,
  "hllString": "%%%AQBjb20udHdpdHRlci5hbGdlYmlyZC5EZW5zZUhMzIIgAgwFBgYEBwYECAYEBQYFBAwEAwgFBQUMCAQFBwgEBAUFBgQGCAoKBwQIBgUIBwUEBwMFBAkHBAQHBgYIBgcECQgIBQcFBQcFCAMJBAUEBAUEAwkGBQcFBQcFBQQFBwUICgYHBgUIBwQEBwUFCQYDCAUDCAoGBgUHBgUHCwQICAMGCQUFBQMKBgcECAYFBQUDBQMECAcFBgUFBQQDBwgHBgUGCQcIBAUFBgcGCAUGBgYGBgcGBQgFBQkHBgYHBQwFBwUFCggEBwYHBAUGBQcFBgYDBwYECgYFBgcFBwYGCAUFBAkGBAQFCAYFBwUFBQYGBQcHBgcFBQQFBwcFAwQGBQYHBAYGBQgGBQYGBQMFCwcEBwcEBAUMBggGBgQJBwUIBQYGAwUEBggHBgUGBAUFBgQGBgYHBgUHBQUGBwMEAwcEBwUMBwcFCAcFCAQDBAcGBAgGBQcHCwQFBQgGAwUKBAYJCAUGBgYICgUFBQYIBQcFAw"
}
```

#### How many distinct users was seen for the loginService at 2015-08-21T04:29:00.000 ?
This endpoint demonstrates the rehydration of Algebird HLL Monoid from String fetched from Redis.
```
$ curl http://localhost:9000/distinct/loginService_2015-08-21T04:29:00.000
{
  "estimatedSize": 99810.66492371981
}
```

### Testing

Execute tests using `test` command:

```
$ sbt
> test
```

## Author & license

If you have any questions regarding this project contact:

@bigsnarfdude on Twitter

For licensing info see LICENSE file in project's root directory.
