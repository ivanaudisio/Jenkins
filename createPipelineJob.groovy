import hudson.plugins.git.*;

scmURL = 'git@github.com:dermeister0/Tests.git'
scmCredential = 'jenkins-credential'
scmBranch = "*/develop"
jenkinsfileName = "Jenkinsfile"
pipelineName = "seedPipeline"

def scm = new GitSCM([new UserRemoteConfig(scmURL, null, null, scmCredential)], [new BranchSpec(scmBranch)], false, null, null, null, null)
def flowDefinition = new org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition(scm, jenkinsfileName)
def parent = Jenkins.instance
def job = new org.jenkinsci.plugins.workflow.job.WorkflowJob(parent, pipelineName)
job.definition = flowDefinition
parent.reload()
