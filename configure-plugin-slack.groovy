import net.sf.json.JSONObject

JSONObject formData = ['slack': ['tokenCredentialId': 'cred_slack']] as JSONObject

def slack = Jenkins.instance.getExtensionList(
  jenkins.plugins.slack.SlackNotifier.DescriptorImpl.class
)[0]
//valid tokens for testing in the jenkins-slack-plugin-test instance of slack.com
def params = [
  slackTeamDomain: '',
  slackToken: '',
  slackRoom: '',
  slackBaseUrl: 'http://localhost:8080/',
  slackSendAs: ''
]
def req = [
  getParameter: { name -> params[name] }
] as org.kohsuke.stapler.StaplerRequest
slack.configure(req, formData)

slack.save()
