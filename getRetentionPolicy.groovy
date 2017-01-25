def jobs = Jenkins.instance.getAllItems() // Get all jenkins items 
 
//Header
println ("Job Name,URL,Days to Keep,Number to keep,Artifact Days to keep,Artifact Num. to keep")
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
          println("${job.name},${template},${job.absoluteUrl},${d.daysToKeep},${d.numToKeep},${d.artifactDaysToKeep},${d.artifactNumToKeep}")
        }else{
          println("${job.name},${template},${job.absoluteUrl},No Retention Policy")
        }
    }
}
println ""
