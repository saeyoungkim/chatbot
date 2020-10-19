name := "chatbot_btd"

lazy val root:Project = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    libraryDependencies ++= Seq(
      guice,
      ws
    ),
    PlayKeys.playDefaultPort := 9191
  )
