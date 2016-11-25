// author : Ivan Audisio
// list jobs being trigguered on a timely basis

import hudson.model.*
import hudson.triggers.*

jobs = Jenkins.instance.getAllItems()

println("------------------------------------------------------------------------")
println("The following jobs are being executed with a CRON job")
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
      
        job.triggers.each{ descriptor, trigger ->
            if(trigger instanceof TimerTrigger) {
            println("Name        : ${job.name}")
            println("Class       : ${job.class}")
            println("Root Dir    : ${job.rootDir}")
            println("URL         : ${job.url}")
            println("Absolute URL: ${job.absoluteUrl}")
            println("Trigger     : "+trigger.spec)
            println("")
            println("")
              
              //job.removeTrigger descriptor
              //job.save()
              //job.addTrigger(new TimerTrigger("$m $hr * * *"))
              //job.save()
                
                           
            }
        }
    }

}
