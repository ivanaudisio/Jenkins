
//items = Jenkins.instance.getAllItems()
//items.each { job ->

Hudson.instance.items.each { job ->

  	// FOLDER
  if (job instanceof com.cloudbees.hudson.plugins.folder.Folder) {
    
    // AUXILIARY TEMPLATE
  } else if (job instanceof com.cloudbees.hudson.plugins.modeling.impl.auxiliary.AuxModel) {
    
    // FOLDER TEMPLATE
  } else if (job instanceof com.cloudbees.hudson.plugins.modeling.impl.folder.FolderTemplate) {
    
  } else if (job instanceof com.cloudbees.hudson.plugins.modeling.impl.jobTemplate.JobTemplate) {
    
  } else {
	println("Name: ${job.name}")
	println("Class: ${job.class}")
  println ''
    
  }
    
  
}
