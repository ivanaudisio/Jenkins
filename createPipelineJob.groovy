import hudson.plugins.git.*;
import groovy.json.JsonSlurper

// Example json configuration for jobs (this should be in a repo)
def restResponse = '''[
	{"url":"git@github.com:dermeister0/Tests.git", "branch":"*/master", "pipelineName":"tes1"},
	{"url":"git@github.com:dermeister0/Tests.git", "branch":"*/master", "pipelineName":"test2"},
	{"url":"git@github.com:dermeister0/Tests.git", "branch":"*/master", "pipelineName":"test3"},
	{"url":"git@github.com:dermeister0/Tests.git", "branch":"*/master", "pipelineName":"test4"}]'''

// Parse the response
def list = new JsonSlurper().parseText( restResponse )

// Creating each pipeline
list.each { 
  scmURL = it['url']
  scmName = null
  scmRefspec = null
  scmCredential = 'jenkins-credential'
  scmBranch = it['branch']
  jenkinsfileName = "Jenkinsfile"
  pipelineName = it['pipelineName']
  
  def scm = new GitSCM([new UserRemoteConfig(scmURL, scmName, scmRefspec, scmCredential)], [new BranchSpec(scmBranch)], false, null, null, null, null)
  def flowDefinition = new org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition(scm, jenkinsfileName)
  def parent = Jenkins.instance
  def job = new org.jenkinsci.plugins.workflow.job.WorkflowJob(parent, pipelineName)
  job.definition = flowDefinition
  parent.reload()
}
