import sbt._

object Dependencies {

  object V {
    val vaadin = "8.1.2"
    val stars = "3.0.0"
    val jetty = "9.4.6.v20170531"
    val avcommons = "1.22.0"
  }

  lazy val ProductionDependencies: List[ModuleID] = List(
    "com.vaadin" % "vaadin-server" % V.vaadin,
    "com.vaadin" % "vaadin-client-compiled" % V.vaadin,
    "com.vaadin" % "vaadin-client-compiler" % V.vaadin,
    "com.vaadin" % "vaadin-themes" % V.vaadin,

    "org.vaadin.addons" % "ratingstars" % V.stars,

    "org.eclipse.jetty" % "jetty-server" % V.jetty,
    "org.eclipse.jetty" % "jetty-servlet" % V.jetty,

    "com.avsystem.commons" %% "commons-core" % V.avcommons,
    "com.avsystem.commons" %% "commons-shared" % V.avcommons,
    "com.avsystem.commons" %% "commons-spring" % V.avcommons,
    "com.avsystem.commons" %% "commons-mongo" % V.avcommons
  )

  lazy val TestDependencies: List[ModuleID] =
    "org.scalatest" %% "scalatest" % "3.0.1" ::
      Nil
}
