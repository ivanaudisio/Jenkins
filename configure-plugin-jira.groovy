// Setting up Jira

import jenkins.model.*
import jenkins.*
import hudson.model.*
import hudson.*
import net.sf.json.JSONArray
import net.sf.json.JSONObject
import org.thoughtslive.jenkins.plugins.jira.Config
import org.thoughtslive.jenkins.plugins.jira.Site

//global user-defined configuration
JSONArray sites = [
  [
    name: 'Jira',
    url: 'http://jira:8080',
    timeout: 10000,
    loginType: 'BASIC',
    userName: 'jira',
    password: 'jira'
  ]
] as JSONArray

//get global Jenkins configuration
Config.ConfigDescriptorImpl config = Jenkins.instance.getExtensionList(Config.ConfigDescriptorImpl.class)[0]

//delete all existing sites
config.@sites.clear()

//configure new sites from the above JSONArray
sites.each { s ->
  String loginType = s.optString('loginType', '').toUpperCase()
  if(loginType in ['BASIC', 'OAUTH']) {
    Site site = new Site(s.optString('name',''), new URL(s.optString('url', '')), s.optString('loginType', ''), s.optInt('timeout', 10000))
    if(loginType == 'BASIC') {
      site.setUserName(s.optString('userName', ''))
      site.setPassword(s.optString('password', ''))
    } else { //loginType is OAUTH
      site.setConsumerKey(s.optString('consumerKey', ''))
      site.setPrivateKey(s.optString('privateKey', ''))
      site.setSecret(s.optString('secret', ''))
      site.setToken(s.optString('token', ''))
    }
    //setSites does not make sense as a name because you can only set one site instead of a list :-/
    config.setSites(site)
  }
}

//persist configuration to disk as XML
config.save()
