# Akka HTTP Algebird example

This project demonstrates the [Akka HTTP](http://doc.akka.io/docs/akka-stream-and-http-experimental/current/scala.html) library. Simple Scala REST service wrapping Algebird HLL library to make an analytics query engine to provide Distinct Counts for millions of items using [HyperLogLog Algorithm](http://algo.inria.fr/flajolet/Publications/FlFuGaMe07.pdf).

The service provides two REST endpoints - one which gives current distinct count on the last minute of activitiy on the requested key. 

The second endpoint aggregates all timestamps matching a specific hour and returns distinct count for that hour on the requested key.

## Usage

Start services with sbt:

```
$ sbt
> ~re-start
```

With the service up, you can start sending HTTP requests:


#### How many users have I seen in the last minute for the login service?
```
$ curl http://localhost:9000/distinct/2015-08-21.loginService
{
  "servername": "loginService",
  "count": "18394",
  "interval": "minute",
  "timestamp": "2015-08-14T12:54:00.000"
}
```



#### How many users have I seen for the day of 2015-08-20 for the login service?
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
