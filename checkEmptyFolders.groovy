// author : Ivan Audisio
// list folders with not items inside

import hudson.model.*
import hudson.triggers.*

println("-----------------------------------------------------------------------------------------------------------------------------")
println("The following Folders are empty")
println("-----------------------------------------------------------------------------------------------------------------------------")
println("")

jobs = Jenkins.instance.getAllItems()

jobs.each {job ->
	if (job instanceof com.cloudbees.hudson.plugins.folder.Folder) {

		items = job.getItems()
		if (items.size() == 0) {
			println("Name        : ${job.name}")
			println("Class       : ${job.class}")
			println("Root Dir    : ${job.rootDir}")
			println("URL         : ${job.url}")
			println("Absolute URL: ${job.absoluteUrl}")
			println("")
			println("")
		}
	}
}

println("-----------------------------------------------------------------------------------------------------------------------------")
