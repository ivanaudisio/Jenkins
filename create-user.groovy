// The following script creates a user under Jenkins own database

import jenkins.model.*
import hudson.security.*

def instance = Jenkins.getInstance()

def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount("admin","admin")
instance.setSecurityRealm(hudsonRealm)
instance.save()

// The following script adds the user under "Matrix-based security" as Admin

def strategy = new GlobalMatrixAuthorizationStrategy()
strategy.add(Jenkins.ADMINISTER, "test")
instance.setAuthorizationStrategy(strategy)
