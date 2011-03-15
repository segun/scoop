import sbt._

class Scoop(info: ProjectInfo) extends DefaultProject(info) with IdeaProject {
	val scalatest = "org.scalatest" % "scalatest" % "1.2" 
	//override def compileOptions = super.compileOptions ++ Seq(Unchecked) ++ Seq(Optimize)
}
