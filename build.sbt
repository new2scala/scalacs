name := "scala misc"

lazy val commonSettings = Seq(
  organization := "org.ditw",
  version := "0.1.0",
  scalaVersion := "2.12.6",
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.5",
    "org.scala-lang" % "scala-reflect" % scalaVersion.value
  )
)

lazy val root = project.in(file(".")).settings(commonSettings: _*).aggregate(scalazTests)
lazy val misc = project.settings(commonSettings: _*)
lazy val scalazTests = project.settings(
  commonSettings,
  libraryDependencies ++= Seq(
    "org.scalaz" %% "scalaz-core" % "7.2.24"
  )
)


