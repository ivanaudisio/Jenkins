items = Jenkins.instance.getAllItems()
items.each { job ->

  countFreeStyle = 0
  countFolders = 0
  countTemplateAux = 0
  countTemplateJob = 0
  countMaven = 0
  countJobs = 0
  countTemplateBuilder = 0
  countTemplateWorkflow = 0
  countBackupProject = 0
  countMatrixProject = 0
  countMultiConfProject = 0
  countMultiBranchProject = 0
  
//Hudson.instance.items.each { job ->
  
  // FREESTYLE
  if (job instanceof hudson.model.FreeStyleProject) { countFreeStyle = 0
 } else if (job instanceof com.cloudbees.hudson.plugins.folder.Folder || job instanceof jenkins.branch.OrganizationFolder) { countFolders = 0
  } else if (job instanceof com.cloudbees.hudson.plugins.modeling.impl.auxiliary.AuxModel) { countTemplateAux = 0
  } else if (job instanceof com.cloudbees.hudson.plugins.modeling.impl.jobTemplate.JobTemplate) { countTemplateJob = 0
  } else if (job instanceof hudson.maven.MavenModule || job instanceof hudson.maven.MavenModuleSet) { countMaven = 0
} else if (job instanceof org.jenkinsci.plugins.workflow.job.WorkflowJob) { countJobs = 0
} else if (job instanceof com.cloudbees.hudson.plugins.modeling.impl.builder.BuilderTemplate) { countTemplateBuilder = 0
} else if (job instanceof com.cloudbees.hudson.plugins.modeling.impl.folder.FolderTemplate) { countTemplateFolder = 0
} else if (job instanceof org.jenkins.plugin.templateWorkflows.TemplatesWorkflowJob) { countTemplateWorkflow = 0
} else if (job instanceof com.infradna.hudson.plugins.backup.BackupProject) { countBackupProject = 0
     // } else if (job instanceof hudson.matrix.MatrixProject) { countMatrixProject = 0
} else if (job instanceof hudson.matrix.MatrixProject) { countMultiConfProject = 0
} else if (job instanceof org.jenkinsci.plugins.workflow.multibranch.WorkflowMultiBranchProject) { countMultiBranchProject = 0
                                                                                      
  } else {
	println("Name: ${job.name}")
	println("Class: ${job.class}")
  println ''
    
  }
    
  
}

println ""
