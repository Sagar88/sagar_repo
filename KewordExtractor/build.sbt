name := "KewordExtractor"

version := "0.1"

scalaVersion := "2.12.4"

mainClass in (Compile,run) := Some("Driver")


libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.4.5",
  "org.apache.spark" %% "spark-sql" % "2.4.5",
  "org.apache.spark" %% "spark-mllib" % "2.4.5",
  "org.scalatest" %% "scalatest" % "3.0.8" % Test

)
