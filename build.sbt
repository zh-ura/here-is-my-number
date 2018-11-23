val ScalatraVersion = "2.4.1"

organization := "com.github.zhura"

name := "here-is-my-number"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.12"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % ScalatraVersion,
  "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
  "ch.qos.logback" % "logback-classic" % "1.2.3" % "runtime",
  "org.eclipse.jetty" % "jetty-webapp" % "9.4.8.v20171121" % "container",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "org.apache.spark" %% "spark-core" % "2.3.2",
  "com.sun.jersey" % "jersey-bundle" % "1.19.2"
)

enablePlugins(SbtTwirl)
enablePlugins(ScalatraPlugin)
