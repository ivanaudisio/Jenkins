import org.csanchez.jenkins.plugins.kubernetes.*
import jenkins.model.*

def j = Jenkins.getInstance()

name = 'jenkins-test-kubernetes'
kubernetesURL = 'https://xxx.xxx.xxx.xxx'
kubernetesNamespace = 'jenkins'
jenkinsURL = 'https://jenkins.test.com'
concurrency = '5'
connectionTimeout = 4
readTimeout = 3

def k = new KubernetesCloud(
  name,
  null,
  kubernetesURL,
  kubernetesNamespace,
  jenkinsURL,
  concurrency, 
  connectionTimeout, 
  readTimeout,
  0
)
k.setSkipTlsVerify(true)
k.setCredentialsId('kubernetes_id')


j.clouds.replace(k)
j.save()
