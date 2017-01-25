def jobs = Jenkins.instance.getAllItems() // Get all jenkins items 
 
//Header
println ("Job Name,URL,Builds: Days to Keep,Builds: Num. to keep,Artifacts: Days to keep,Artifacts: Num. to keep,Template")
jobs.each { job -> 

  	template = ""
	// Verify that the item contains the method getLastBuild (avoids templates and folders)  
	if (job.metaClass.getMetaMethod("getLastBuild")) { 
      	
        // Get template if the job is an instance
        def model = com.cloudbees.hudson.plugins.modeling.impl.entity.EntityInstance.from(job)
        if (model) {
            template = "${model.modelId}" // Get template path
        }

        // Get Retention policy
        def d = job.buildDiscarder
        
      	// Print Line with information found
      	if (d){
          println("${job.name},${job.absoluteUrl},${d.daysToKeep},${d.numToKeep},${d.artifactDaysToKeep},${d.artifactNumToKeep},${template}")
        }else{
          println("${job.name},${job.absoluteUrl},No Retention Policy,,,,${template}")
        }
    }
}
println ""
