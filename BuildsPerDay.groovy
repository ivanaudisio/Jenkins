// author : Ivan Audisio
// list jobs not run in the last N days / last N months

import groovy.time.TimeCategory
import java.text.SimpleDateFormat;

jobs = Jenkins.instance.getAllItems()	// Get all jenkins items

days = 3
headerString = "Job"


for (i = days; i > 0; i--) {
  header = new Date() - (i - 1)
  header = header.format( 'd-M-yyyy' )
  headerString += ";" + header
}

println("${headerString}")


jobs.each {job ->

	// Verify that the item contains the method getLastBuild (avoids templates and folders) 
	if (job.metaClass.getMetaMethod("getLastBuild")) {
  	  // Verifies that the job has been build at leat once

      buildString = ""
      
      for (i = days; i > 0; i--) {

  			def count = 0
             start = new Date() - (i - 1)
            start = start.getTime()
            end = new Date() - i
            end =  end.getTime();      
            builds = job.getBuilds().byTimestamp(end, start)
            builds.each{build -> count ++}
            buildString += ";${count}"        
      }
      
      println("${job.name}${buildString}")
      //println("Methods		: " + job.metaClass.methods*.name.sort().unique())

	}
}
println("")
