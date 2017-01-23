//USE CASE
//I have a job template (named job_template) with a parameter param. 
//From this template I create a job instance (named job_instance) with the value X for param. 
//I want to replace X by Y using a script.

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
