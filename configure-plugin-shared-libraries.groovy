import org.jenkinsci.plugins.workflow.libs.SCMSourceRetriever;
import org.jenkinsci.plugins.workflow.libs.LibraryConfiguration;
import jenkins.plugins.git.GitSCMSource;

def scmId = "git-library-id"
def gitRepo = "http://my/repo.git"
def gitCredentialId = "git-credential-id"
  
def globalLibsDesc = Jenkins.getInstance()
        .getDescriptor("org.jenkinsci.plugins.workflow.libs.GlobalLibraries")
SCMSourceRetriever retriever = new SCMSourceRetriever(new GitSCMSource(
        scmId,
        gitRepo,
        gitCredentialId,
        "*",
        "",
        false))
LibraryConfiguration pipeline = new LibraryConfiguration("nexus-library", retriever)
pipeline.setDefaultVersion("master")
pipeline.setImplicit(true)
pipeline.setAllowVersionOverride(true)
pipeline.setIncludeInChangesets(true)
globalLibsDesc.get().setLibraries([pipeline])
