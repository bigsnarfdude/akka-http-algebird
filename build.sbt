enablePlugins(JavaAppPackaging)

name         := "akka-http-algebird"
organization := "com.example"
version      := "1.0"
scalaVersion := "2.11.7"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV       = "2.3.12"
  val akkaStreamV = "1.0"
  val scalaTestV  = "2.2.5"
  Seq(
    "com.typesafe.akka" %% "akka-actor"                           % akkaV,
    "com.typesafe.akka" %% "akka-stream-experimental"             % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-core-experimental"          % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-experimental"               % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-spray-json-experimental"    % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-testkit-experimental"       % akkaStreamV,
    "org.scalatest"     %% "scalatest"                            % scalaTestV % "test",
    "com.github.seratch" %% "awscala" % "0.5.+",
    "com.twitter" %% "algebird-core" % "0.11.0",
    "com.twitter" %% "chill" % "0.7.1",
    "com.twitter" %% "chill-algebird" % "0.7.1",
    "com.twitter" %% "chill-bijection" % "0.7.1",
    "net.debasishg" %% "redisclient" % "2.13"
  )
}

Revolver.settings
