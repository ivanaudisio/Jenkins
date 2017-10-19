import jenkins.model.*
import hudson.security.*
import hudson.plugins.*
import hudson.plugins.active_directory.*
import hudson.*
import jenkins.*
import jenkins.model.*


def instance = Jenkins.getInstance()
String domain = 'my.domain.com'
String site = 'site'
String server = '127.0.0.0'
String bindName = 'account@my.domain.com'
String bindPassword = 'password'
adrealm = new ActiveDirectorySecurityRealm(domain, site, bindName, bindPassword, server)
instance.setSecurityRealm(adrealm)
