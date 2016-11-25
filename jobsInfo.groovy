import groovy.time.TimeCategory
use ( TimeCategory ) {
  // e.g. find jobs not run in last 3 months
  sometimeago = (new Date() - 1.hour)
}

Hudson.instance.items.each { job ->

	def jobType = "${job.class}"
	def today = new Date().format( 'E MMM dd H:m:s z yyyy' )

    println("Class       : ${jobType}")
    println("Name        : ${job.name}")
    println("Root Dir    : ${job.rootDir}")
    println("URL         : ${job.url}")
    println("Absolute URL: ${job.absoluteUrl}")

    //println job.toString()


    if (!jobType.contains("Template") && !jobType.contains("Folder")) {

        println ("Last Build	: " + job.getLastBuild().getTime() )

      if (job.getLastBuild().getTime() < sometimeago) {
      println ("older than 1 hour")
      }else{
      println ("newer than 1 hour")
      }
      
        jobTriggers = job.getTriggers()
        jobTriggers.each { trigger ->
                println("trigger: ${trigger}")
        }

        if (jobType.contains("FreeStyle")) {
            //println("Timer Trigger: ${job.TimerTrigger}")
        }

    }

    println("")
    println("Methods		: " + job.metaClass.methods*.name.sort().unique())
    println("")
    job.metaClass.properties.each {println it.name }
    println("")
    println("")

}
