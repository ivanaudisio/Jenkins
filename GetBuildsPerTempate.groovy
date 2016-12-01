import groovy.time.TimeCategory
import java.text.SimpleDateFormat;

items = Jenkins.instance.getAllItems()	// Get all jenkins items

count = 0
days = 3
total = 0
buildString = ""
headerString = "Job;Template"

for (i = days; i > 0; i--) {
	header = new Date() - (i - 1)
	header = header.format( 'd-M-yyyy' )
	headerString += ";" + header
}

println("${headerString};Total")

items.each {item ->

if (item instanceof com.cloudbees.hudson.plugins.modeling.impl.jobTemplate.JobTemplate) {

	instances = item.listInstances()

	instances.each{instance -> 
      
      job = jenkins.model.Jenkins.instance.getItem("${instance.name}")
      
		if (job.metaClass.getMetaMethod("getLastBuild")) {
			// Verifies that the job has been build at leat once

			buildString = ""
total = 0
			for (i = days; i > 0; i--) {

				def count = 0
				 start = new Date() - (i - 1)
				start = start.getTime()
				end = new Date() - i
				end =  end.getTime();      
				builds = job.getBuilds().byTimestamp(end, start)
				builds.each{build -> count ++}
				buildString += ";${count}"
              	total += count
			}
		}

      println("${job.name};${item.name}${buildString};${total}")

		}
	}

}
println("")
