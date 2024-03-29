val Http4sVersion         = "0.21.4"
val CirceVersion          = "0.13.0"
val Log4CatsVersion       = "1.0.1"
val DoobieVersion         = "0.8.8"
val Slf4jVersion          = "1.7.5"
val PureConfigVersion     = "0.12.3"
val FlywayVersion         = "2.5.0"
val ZioVersion            = "1.0.1"
val ZioInteropCatsVersion = "2.1.4.0"

enablePlugins(FlywayPlugin)

lazy val flywaySettings = Seq(
  flywayUrl := "jdbc:postgresql://localhost:5432/zioplayground",
  flywayUser := "postgres",
  flywayPassword := "1234",
  flywayLocations += "db/migration",
  flywaySchemas += "public"
)

lazy val root = (project in file("."))
  .enablePlugins(FlywayPlugin)
  .settings(flywaySettings)
  .settings(
    organization := "slouc",
    name := "zio-playground",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.13.3",
    libraryDependencies ++= Seq(
      "io.circe"              %% "circe-generic"       % CirceVersion,
      "io.chrisdavenport"     %% "log4cats-slf4j"      % Log4CatsVersion,
      "org.slf4j"             % "slf4j-simple"         % Slf4jVersion,
      "org.tpolecat"          %% "doobie-core"         % DoobieVersion,
      "org.tpolecat"          %% "doobie-postgres"     % DoobieVersion,
      "com.github.pureconfig" %% "pureconfig"          % PureConfigVersion,
      "org.hsqldb"            % "hsqldb"               % FlywayVersion
    ) ++ httpDependencies ++ zioDependencies,
    addCompilerPlugin("org.typelevel" %% "kind-projector"     % "0.10.3"),
    addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % "0.3.0")
  )

lazy val httpDependencies = Seq(
  "org.http4s"            %% "http4s-blaze-server" % Http4sVersion,
  "org.http4s"            %% "http4s-blaze-client" % Http4sVersion,
  "org.http4s"            %% "http4s-circe"        % Http4sVersion,
  "org.http4s"            %% "http4s-dsl"          % Http4sVersion,
)

lazy val zioDependencies = Seq(
  "dev.zio" %% "zio"              % ZioVersion,
  "dev.zio" %% "zio-interop-cats" % ZioInteropCatsVersion
)

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-feature",
  "-Xfatal-warnings"
)

onChangedBuildSource in Global := ReloadOnSourceChanges

fork in run := true
outputStrategy := Some(StdoutOutput)
