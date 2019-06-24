def daysToKeep = 28
def numToKeep = 10
def artifactDaysToKeep = -1
def artifactNumToKeep = -1
 
Jenkins.instance.items.each { item ->
  println("=====================")
  println("JOB: " + item.name)
  println("Job type: " + item.getClass())
 
  if(item.buildDiscarder == null) {
    println("No BuildDiscarder")
    println("Set BuildDiscarder to LogRotator")
  } else {
    println("BuildDiscarder: " + item.buildDiscarder.getClass())
    println("Found setting: " + "days to keep=" + item.buildDiscarder.daysToKeepStr + "; num to keep=" + item.buildDiscarder.numToKeepStr + "; artifact day to keep=" + item.buildDiscarder.artifactDaysToKeepStr + "; artifact num to keep=" + item.buildDiscarder.artifactNumToKeepStr)
    println("Set new setting")
  }
 
  item.buildDiscarder = new hudson.tasks.LogRotator(daysToKeep,numToKeep, artifactDaysToKeep, artifactNumToKeep)
  item.save()
  println("")
 
}
