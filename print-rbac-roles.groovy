import jenkins.model.Jenkins
import hudson.model.User

allUsers = User.getAll()
adminList = []
test = Jenkins.instance.getAuthorizationStrategy()

test.metaClass.methods*.name.sort().unique()

roles = test.getGrantedRoles("globalRoles")

    for (entry in roles) {
        role = entry.key
        users = entry.value
      
      println role.getName() + " = " + users
      
    }
