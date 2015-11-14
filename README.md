# Project Rehydrate
## Akka HTTP Algebird example

This project demonstrates the [Akka HTTP](http://doc.akka.io/docs/akka-stream-and-http-experimental/current/scala.html) library. Simple Scala REST service wrapping Twitter Algebird HLL library to make an analytics query engine to provide Distinct Counts for millions of items using [HyperLogLog Algorithm](http://algo.inria.fr/flajolet/Publications/FlFuGaMe07.pdf).

HLLs are stored as Base64 encoded strings using Twitter Chill(Kryo under the covers) for every minute. 

Key: `loginService-2015-08-21T04:29:00.000` 
HLLValue: `encodedHLLString`
Value: `18394`


The service provides two REST endpoints - one which gives current distinct count on the last minute of activitiy on the requested key. 

The second endpoint aggregates all timestamps matching a specific day and returns distinct count for the entire day on the requested key.

## Usage

Start services with sbt:

```
$ sbt run
```

With the service up, you can start sending HTTP requests to the different endpoints:


#### Add UUID value to the loginService and return the distinct users seen for the interval:
```
$ curl -X POST -H 'Content-Type: application/json' http://localhost:9000/addHLL -d '{"servername": "loginService", "value": "4cd4f31f-3de2-4428-b457-04b75396214e"}'
{
  "servername": "loginService",
  "estimatedSize": 138543.13492371982,
  "interval": "day",
  "timestamp": "2015-08-21T00:00:00.000"
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


#### How many users have I seen for the day of 2015-08-20 for the login service?
This example demonstrates the rehydration of a collection of HLL Monoids from collection of Strings fetched from Redis. In addition, we demonstrate Algebird's native methods to Sum all the HLL Monoids and give us a distinct count for the day.
```
$ curl -X POST -H 'Content-Type: application/json' http://localhost:9000/distinct -d '{"begin": "2015-08-20", "servername": "loginService"}'
{
  "servername": "loginService",
  "count": "138543",
  "interval": "day",
  "timestamp": "2015-08-20T00:00:00.000"
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
