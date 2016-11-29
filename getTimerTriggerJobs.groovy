// author : Ivan Audisio
// list jobs being trigguered on a timely basis

import hudson.model.*
import hudson.triggers.*

jobs = Jenkins.instance.getAllItems()	// Get all jobs
def count = 0				// Counts number of jobs found using CRON
  
println("------------------------------------------------------------------------")
println("The following jobs are being executed with a CRON job")
println("------------------------------------------------------------------------")
println("")

jobs.each {job ->
	// Check if Job contains the method getTriggers (skips templates and folders)
	if (job.metaClass.getMetaMethod("getTriggers")) {
		// Gets CRON triggers from Job
		job.triggers.each{ descriptor, trigger ->
			// Verifies that the specs have been entered and prints the job's information
			if(trigger instanceof TimerTrigger) {
				if (trigger.spec) {
					count ++
					println("Name        : ${job.name}")
					println("Class       : ${job.class}")
					println("Root Dir    : ${job.rootDir}")
					println("URL         : ${job.url}")
					println("Absolute URL: ${job.absoluteUrl}")
					println("Trigger     : "+trigger.spec)
					println("")
					println("")
					//The following code can be used to modify the triggers if needed.
					//job.removeTrigger descriptor
					//job.save()
					//job.addTrigger(new TimerTrigger("$m $hr * * *"))
					//job.save()				
				}
			}
		}
	}
}
println("------------------------------------------------------------------------")
println("${count} job(s) are being executed with CRON")
println("------------------------------------------------------------------------")
