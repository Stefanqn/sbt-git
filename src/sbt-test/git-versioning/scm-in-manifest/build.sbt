import com.typesafe.sbt.SbtGit
import sbt.Setting

enablePlugins(GitVersioning)



val checkManifestContainsSCM = taskKey[Unit]("checks that the generated manifest contains source code managment information")
checkManifestContainsSCM := {
  val packageOpts = (Compile / packageBin / packageOptions).value
  val hasSCM = packageOpts.exists(_.toString.contains("SCM"))
  assert(hasSCM, s"Failed to find SCM information in packageOptions. Only found ${packageOpts.map(_.toString).mkString(",")}")
}

val checkManifestNotContainsSCM = taskKey[Unit]("checks that the generated manifest does not contains source code managment information")
checkManifestNotContainsSCM := {
  val packageOpts = (Compile / packageBin / packageOptions).value
  val hasSCM = packageOpts.exists(_.toString.contains("SCM"))
  assert(!hasSCM, s"Found SCM information in packageOptions: ${packageOpts.map(_.toString).mkString(",")}")
}

