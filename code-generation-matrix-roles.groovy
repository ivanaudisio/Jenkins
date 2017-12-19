import jenkins.model.Jenkins
import hudson.model.User

authorization = Jenkins.instance.getAuthorizationStrategy()

roles = authorization.getGrantedRoles("globalRoles")
def allUsers = ""

println '''
import hudson.*
import hudson.security.*
import jenkins.model.*
import java.util.*
import com.michelin.cio.hudson.plugins.rolestrategy.*
import java.lang.reflect.*
import java.util.logging.*
import groovy.json.*

def env = System.getenv()

//* ===================================      
//*                Roles
//* ===================================
''' 
for (entry in roles) {
	role = entry.key
    println "def globalRole" + role.getName() + " = '" + role.getName() + "'"
}

println """
//* ===================================      
//*           Users and Groups
//* ===================================
"""

println "def access = ["
for (entry in roles) {
	role = entry.key
	users = entry.value
  	users.each {
    	allUsers = allUsers + '"' + it + '",'
  	}
    println "\t" + role.getName() + ": [" + allUsers + "],"
  	allUsers = ""
}
println "]"

println """
//* ===================================         
//*           Permissions
//* ===================================
"""

for (entry in roles) {
	role = entry.key
  	println "def " + role.getName() + "Permissions = ["
  
    role.permissions.each {
    	println '"' + it.id + '",'
    }
  	println ']'
}

println """
// Set Role Based Access Control Authorization strategy
def roleBasedAuthenticationStrategy = new RoleBasedAuthorizationStrategy()
Jenkins.instance.setAuthorizationStrategy(roleBasedAuthenticationStrategy)

Constructor[] constrs = Role.class.getConstructors();
for (Constructor<?> c : constrs) {
  c.setAccessible(true);
}

// Make the method assignRole accessible
Method assignRoleMethod = RoleBasedAuthorizationStrategy.class.getDeclaredMethod("assignRole", String.class, Role.class, String.class);
assignRoleMethod.setAccessible(true);

//* ===================================     
//*           Permissions
//* ===================================
"""

for (entry in roles) {
	role = entry.key
	users = entry.value
    println """
Set<Permission> """ + role.getName() + """PermissionSet = new HashSet<Permission>();
""" + role.getName() + """Permissions.each { p ->
  def permission = Permission.fromId(p);
  if (permission != null) {
    """ + role.getName() + """PermissionSet.add(permission);
  } else {
    println("\${p} is not a valid permission ID (ignoring)")
  }
}
"""
}

println """
//* ===================================
//*      Permissions -> Roles
//* ===================================
"""

for (entry in roles) {
	role = entry.key
println """// """ + role.getName() + """
Role """ + role.getName() + """Role = new Role(globalRole""" + role.getName() + """, """ + role.getName() + """PermissionSet);
roleBasedAuthenticationStrategy.addRole(RoleBasedAuthorizationStrategy.GLOBAL, """ + role.getName() + """Role);
"""
}

println """
//* ===================================      
//*      Roles -> Groups/Users
//* ===================================
"""

for (entry in roles) {
	role = entry.key
println """
access.""" + role.getName() + """.each { l ->
  println("Granting """ + role.getName() + """ to \${l}")
  roleBasedAuthenticationStrategy.assignRole(RoleBasedAuthorizationStrategy.GLOBAL, """ + role.getName() + """Role, l);  
}
"""
}

println "Jenkins.instance.save()"
