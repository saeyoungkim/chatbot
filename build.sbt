name := "chatbot_btd"

lazy val root:Project = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    libraryDependencies += guice,
    PlayKeys.playDefaultPort := 9191
  )
