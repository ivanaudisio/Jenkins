// author : Ivan Audisio
// list jobs being trigguered with a pool

import hudson.model.*
import hudson.triggers.*

println("-----------------------------------------------------------------------------------------------------------------------------")
println("The following jobs are being executed with a Poll")
println("-----------------------------------------------------------------------------------------------------------------------------")
println("")

jobs = Jenkins.instance.getAllItems()

jobs.each {job ->
  
  if (job instanceof com.cloudbees.hudson.plugins.folder.Folder) { return }
  if (job instanceof com.cloudbees.hudson.plugins.modeling.impl.jobTemplate.JobTemplate) { return }
      
        job.triggers.each{ descriptor, trigger ->
            if(trigger instanceof SCMTrigger) {
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

println("-----------------------------------------------------------------------------------------------------------------------------")
