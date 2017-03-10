//import hudson.model.*
// per http://stackoverflow.com/questions/17429050/running-groovy-command-from-jenkins-using-groovy-script-plugin
// requires this now
import jenkins.model.Jenkins 

def q = Jenkins.instance.queue

q.items.each { q.cancel(it.task) }
