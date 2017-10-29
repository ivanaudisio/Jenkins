import jenkins.model.*
import hudson.security.*

def instance = Jenkins.getInstance()

// Create new security realm
def hudsonRealm = new HudsonPrivateSecurityRealm(false)

// Create new user under Jenkins' own DB
hudsonRealm.createAccount("MyUSERNAME","MyPASSWORD")

// Set Jenkins DB as new security realm
instance.setSecurityRealm(hudsonRealm)
instance.save()
