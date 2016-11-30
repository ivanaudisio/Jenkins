// author : Ivan Audisio
// List the number of builds for each job in the past X days

import groovy.time.TimeCategory
import java.text.SimpleDateFormat;

days = 10

def start = new Date()
start = start.getTime()
def end = new Date() - days
end =  end.getTime()

jobs = Jenkins.instance.getAllItems()	// Get all jenkins items

println("------------------------------------------------------------------------")
println("The following jobs have not executed since ")
println("------------------------------------------------------------------------")
println("")

jobs.each {job ->
  
  def count = 0
	// Verify that the item contains the method getLastBuild (avoids templates and folders) 
	if (job.metaClass.getMetaMethod("getLastBuild")) {
		// Verifies that the job has been build at leat once
		builds = job.getBuilds().byTimestamp(end, start)
		
      builds.each{build -> count ++}
      
      println("Name        : ${job.name} (${count} builds)")
	}
}
println("------------------------------------------------------------------------")
println("${count} Job(s) have not been executed since ")
println("${countNotBuild} Job(s) do not have any build history")
println("------------------------------------------------------------------------")
