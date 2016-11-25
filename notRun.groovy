// author : Ivan Audisio
// list jobs not run in the last N days / last N months

import groovy.time.TimeCategory
use(TimeCategory) {
  // e.g. find jobs not run in last 6 months
  //timeFrame = (new Date() - 6.months)
  // e.g. find jobs not run in last 3 hours
  timeFrame = (new Date() - 3. hour)
  // e.g. find jobs not run in last day
	//timeFrame = (new Date() - 1.days)
}

def inspect = false;

jobs = Jenkins.instance.getAllItems()
lastabort = null

println("------------------------------------------------------------------------")
println("The following jobs have not executed since ${timeFrame}")
println("------------------------------------------------------------------------")
println("")

jobs.each {job ->
    
	if (job instanceof com.cloudbees.hudson.plugins.folder.Folder) {
    	inspect = false
	} else if (job instanceof com.cloudbees.hudson.plugins.modeling.impl.jobTemplate.JobTemplate) {
        inspect = false
    } else {
        inspect = true
    }

    if (inspect) {
        if (job.getLastBuild().getTime() < timeFrame) {
            println("Name        : ${job.name}")
            println("Class       : ${job.class}")
            println("Root Dir    : ${job.rootDir}")
            println("URL         : ${job.url}")
            println("Absolute URL: ${job.absoluteUrl}")
            println("Last Build  :" + job.getLastBuild().getTime())
            println("")
            println("")
        }
    }
}
