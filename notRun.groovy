// author : Ivan Audisio
// list jobs not run in the last N days / last N months

import groovy.time.TimeCategory

use(TimeCategory) {
	// e.g. find jobs not run in last 6 months
	//timeFrame = (new Date() - 6.months)
	// e.g. find jobs not run in last 3 hours
	//timeFrame = (new Date() - 3.hours)
	// e.g. find jobs not run in last day
	//timeFrame = (new Date() - 1.days)
	timeFrame = (new Date() - 6.months)
}

jobs = Jenkins.instance.getAllItems()	// Get all jenkins items
def count = 0
def countNotBuild = 0

println("------------------------------------------------------------------------")
println("The following jobs have not executed since ${timeFrame}")
println("------------------------------------------------------------------------")
println("")

jobs.each {job ->
	// Verify that the item contains the method getLastBuild (avoids templates and folders) 
	if (job.metaClass.getMetaMethod("getLastBuild")) {
		// Verifies that the job has been build at leat once
		if (job.getLastBuild()){
			if (job.getLastBuild().getTime() < timeFrame) {
				count ++
				println("Name        : ${job.name}")
				println("Class       : ${job.class}")
				println("Root Dir    : ${job.rootDir}")
				println("URL         : ${job.url}")
				println("Absolute URL: ${job.absoluteUrl}")
				println("Last Build  : " + job.getLastBuild().getTime())
				println("")
				println("")
			} 
		} else {
			countNotBuild ++
			println("Name        : ${job.name}")
			println("Class       : ${job.class}")
			println("Root Dir    : ${job.rootDir}")
			println("URL         : ${job.url}")
			println("Absolute URL: ${job.absoluteUrl}")
			println("Last Build  : There is no build history for this Job")
			println("")
			println("")	
		}
	}
}
println("------------------------------------------------------------------------")
println("${count} Job(s) have not been executed since ${timeFrame}")
println("${countNotBuild} Job(s) do not have any build history")
println("------------------------------------------------------------------------")
