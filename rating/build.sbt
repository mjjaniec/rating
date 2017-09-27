import Dependencies._

lazy val root = (project in file("."))
  .settings(vaadinWebSettings: _*)
  .settings(
    inThisBuild(List(
      organization := "com.avsystem",
      scalaVersion := "2.12.1",
      version := "0.1.0"
    )),
    name := "Rating",
    resolvers += "Vaadin addons" at "http://maven.vaadin.com/vaadin-addons",
    libraryDependencies ++= ProductionDependencies ++ TestDependencies.map(_ % Test),
    vaadinWidgetsets := Seq("com.avsystem.rating.WidgetSet"),
    target in compileVaadinWidgetsets := (sourceDirectory in Compile).value / "WebContent" / "VAADIN" / "widgetsets"
  )
