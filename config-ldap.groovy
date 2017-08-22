import jenkins.*
import jenkins.model.*
import hudson.*
import hudson.model.*
import hudson.security.LDAPSecurityRealm
import hudson.util.Secret
import jenkins.model.IdStrategy
import jenkins.security.plugins.ldap.FromUserRecordLDAPGroupMembershipStrategy
import jenkins.security.plugins.ldap.FromGroupSearchLDAPGroupMembershipStrategy
import jenkins.security.plugins.ldap.LDAPGroupMembershipStrategy

// Getting Jenkins instance
def jenkins_instance = Jenkins.getInstance()

jenkins_instance.pluginManager.plugins.each { plugin ->

  if (plugin.getShortName().contains("ldap")) { // Validate if ldap plugin is installed
    def ldap_server = 'ldap://ldap-service:389'
    def ldap_rootDN = 'dc=ldap,dc=cloudbees,dc=training,dc=local'
    boolean ldap_inhibitInferRootDN = ''
    def ldap_userSearchBase = 'ou=people'
    def ldap_userSearch = 'uid={0}'
    def ldap_groupSearchBase = 'ou=groups'
    def ldap_groupSearchFilter = 'cn={0}'
    LDAPGroupMembershipStrategy ldap_groupMembershipStrategy = new FromUserRecordLDAPGroupMembershipStrategy('memberOf')
    //LDAPGroupMembershipStrategy ldap_groupMembershipStrategy = new FromGroupSearchLDAPGroupMembershipStrategy('(&(objectCategory=group)(member={0}))')
    def ldap_managerDN = ''
    def ldap_managerPasswordSecret = Secret.fromString('')
    boolean ldap_disableMailAddressResolver = ''
    def ldap_displayNameAttributeName = 'displayname'
    def ldap_mailAddressAttributeName = 'mail'

    // Default values
    IdStrategy ldap_userIdStrategy = new IdStrategy.CaseSensitive();
    IdStrategy ldap_groupIdStrategy = new IdStrategy.CaseSensitive();

    LDAPSecurityRealm.EnvironmentProperty[] ldap_environmentProperties = [
      new LDAPSecurityRealm.EnvironmentProperty('KeyOne', 'ValueOne'),
      new LDAPSecurityRealm.EnvironmentProperty('KeyTwo', 'ValueTwo'),
      new LDAPSecurityRealm.EnvironmentProperty('KeyThree', 'KeyThree')
    ]
    
    // Default values
    LDAPSecurityRealm.CacheConfiguration ldap_cacheConfiguration = new LDAPSecurityRealm.CacheConfiguration(0, 0)

    Jenkins.instance.with {
      securityRealm =
        new LDAPSecurityRealm(
          ldap_server,
          ldap_rootDN,
          ldap_userSearchBase,
          ldap_userSearch,
          ldap_groupSearchBase,
          ldap_groupSearchFilter,
          ldap_groupMembershipStrategy,
          ldap_managerDN,
          ldap_managerPasswordSecret,
          ldap_inhibitInferRootDN,
          ldap_disableMailAddressResolver,
          ldap_cacheConfiguration,
          ldap_environmentProperties,
          ldap_displayNameAttributeName,
          ldap_mailAddressAttributeName,
          ldap_userIdStrategy, ldap_groupIdStrategy
        )
    }
  }
}
