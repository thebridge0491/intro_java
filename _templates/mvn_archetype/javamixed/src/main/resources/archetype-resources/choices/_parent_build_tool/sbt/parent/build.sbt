#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
import Settings._

ThisBuild / scalaVersion := "2.13.2"
ThisBuild / organization := "${groupId}"
ThisBuild / organizationName := "Coding Sandbox"

lazy val parent = project.in(file("."))
  .settings(defaultSettings:_*)
  .settings(name := "${parent}-parent"
    , version := "0"

    // ?? deprecated since sbt 1.5
    //, externalIvySettingsURL(url("file://" +
    //  sys.env.getOrElse("HOME", ".") + "/.ivy2/ivysettings.xml"))
    //, externalIvySettings(Def.setting(file(sys.env.getOrElse("HOME",
    //  ".") + "/.ivy2/ivysettings.xml")))
    //, externalIvyFile()

    /*, libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-library" % "2.13.2"
      , "org.scalatest" %% "scalatest" % "3.2.10" % Test
      )*/
    , Test / run := { () }
    , Compile / run := { () }
    , Compile / classpathConfiguration := Compile
    , Runtime / classpathConfiguration := Runtime
    , Test / classpathConfiguration := Test
    , publishArtifact := false
    )

//lazy val scalaCompat = sys.props.getOrElse("scala.compat", "2.13")
