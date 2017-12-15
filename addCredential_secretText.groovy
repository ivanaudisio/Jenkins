import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.common.*
import com.cloudbees.plugins.credentials.domains.*

global_domain = Domain.global()
credentials_store = Jenkins.instance.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')[0].getStore()

credentials = new org.jenkinsci.plugins.plaincredentials.impl.StringCredentialsImpl(
    CredentialsScope.GLOBAL,
    'credential_id',
    'credential description',
    new hudson.util.Secret('this is the secret text'))

credentials_store.addCredentials(global_domain, credentials)
