import jenkins.*
import jenkins.model.*
import hudson.*
import hudson.model.* 
	
def jobs = Jenkins.instance.getAllItems() // Get all jenkins items 
 
//Header
println ("Job Name,URL,Builds: Days to Keep,Builds: Num. to keep,Artifacts: Days to keep,Artifacts: Num. to keep,Template,Size Kilobytes")
jobs.each { job -> 

  	template = ""
  	modelFound = true
	
	// Verify that the item contains the method getLastBuild (avoids templates and folders)  
	if (job.metaClass.getMetaMethod("getLastBuild")) { 
      	
        // Get template if the job is an instance
      	try {
        	def model = com.cloudbees.hudson.plugins.modeling.impl.entity.EntityInstance.from(job)
        } catch (MissingPropertyException e){
        	modelFound = false
        }
        if (modelFound) {
            template = "${model.modelId}" // Get template path
        }
      	
      	// Get Build folder size for job
       	// buildFolder = new File("${job.rootDir}")
	// buildFolderSize = buildFolder.directorySize() * 0.001
	
	// Use Shell to get size
	//path = "${job.rootDir}/builds"
	// buildFolderSize = "du -hbs ${path}".execute().text
	// buildFolderSize = buildFolderSize.replace("\n","").replace("\r\n","");
      	// buildFolderSize = buildFolderSize.split("\t")
       	// size = buildFolderSize[0]
		
        // Get Retention policy
        def d = job.buildDiscarder
        
      	// Print Line with information found
      	if (d){
          println("${job.name},${job.absoluteUrl},${d.daysToKeep},${d.numToKeep},${d.artifactDaysToKeep},${d.artifactNumToKeep},${template},This is a test") //,${size}")
        }else{
          println("${job.name},${job.absoluteUrl},No Retention Policy,,,,${template}") //,${size}")
        }
    }
}
println ""
