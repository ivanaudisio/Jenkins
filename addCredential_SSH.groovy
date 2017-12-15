import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.common.*
import com.cloudbees.plugins.credentials.domains.*
import com.cloudbees.jenkins.plugins.sshcredentials.impl.*
import hudson.plugins.sshslaves.*;

global_domain = Domain.global()
credentials_store =
Jenkins.instance.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')[0].getStore()
credentials = new BasicSSHUserPrivateKey(
  CredentialsScope.GLOBAL,
  "credential_id",
  "user",
  new BasicSSHUserPrivateKey.DirectEntryPrivateKeySource(""" PRIVATE KEY """),
  "",
  "description"
)
credentials_store.addCredentials(global_domain, credentials)
