// author : Ivan Audisio
// list jobs not run in the last N days / last N months

import groovy.time.TimeCategory
import java.text.SimpleDateFormat;

jobs = Jenkins.instance.getAllItems()	// Get all jenkins items

def days = 3
def headerString = "Job;Template"
def totalBuilds = 0

for (i = days; i > 0; i--) {
  header = new Date() - (i - 1)
  header = header.format( 'd-M-yyyy' )
  headerString += ";" + header
}

println("${headerString};Total Builds")


jobs.each {job ->

	// Verify that the item contains the method getLastBuild (avoids templates and folders) 
	if (job.metaClass.getMetaMethod("getLastBuild")) {

		templateName = ""
		buildString = ""
		  
		for (i = days; i > 0; i--) {
			count = 0
			start = new Date() - (i - 1)
			start = start.getTime()
			end = new Date() - i
			end =  end.getTime();      
			builds = job.getBuilds().byTimestamp(end, start)
			builds.each{build -> count ++}
			buildString += ";${count}"  
			totalBuilds += count			
		}
		
		// Determines if the Job is build from a Template
		def model = com.cloudbees.hudson.plugins.modeling.impl.entity.EntityInstance.from(job)
		if (model) {
			templateName = "${model.modelId}"
		}

		// Print Row in CSV format "Job,Tempalte,Day1,Day2,...,Total Builds"
		println("${job.name};${templateName}${buildString};${totalBuilds}")
	}
}
println("")
