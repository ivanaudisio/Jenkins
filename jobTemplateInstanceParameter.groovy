// Let's take the job to update
def job = Jenkins.instance.getItem('job_instance')
println "Updating job: ${job.fullName}"
// Let's find the instance object used to link the job to its template
def instance = com.cloudbees.hudson.plugins.modeling.impl.entity.EntityInstance.from(job)

println "* Current value of parameter is : ${instance.getValue("param")}"
// Let's update
instance.setValue("param","Y")
instance.save()
println "* New value of parameter is : ${instance.getValue("param")}"
