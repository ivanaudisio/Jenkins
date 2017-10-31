import jenkins.model.Jenkins
import hudson.model.User

authorization = Jenkins.instance.getAuthorizationStrategy()

roles = authorization.getGrantedRoles("globalRoles")

for (entry in roles) {
	role = entry.key
	users = entry.value
    println role.getName() + " = " + users  
}
println '----------------------------'

for (entry in roles) {
	role = entry.key
  	println role.getName()
    role.permissions.each {
    	println it.id
    }
  	println '----------------------------'
}
