val scala3Version = "3.2.1"

val scalacticVersion = "3.2.14"
lazy val root = project
  .in(file("."))
  .settings(
    name := "Advent_2022",
    version := "1.0",

    libraryDependencies += "org.scalactic" %% "scalactic" % scalacticVersion,
    libraryDependencies += "org.scalatest" %% "scalatest" % scalacticVersion % "test",

    // To make the default compiler and REPL use Dotty
    scalaVersion := scala3Version
  )
