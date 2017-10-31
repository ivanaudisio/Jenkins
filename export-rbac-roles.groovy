authorization = Jenkins.instance.getAuthorizationStrategy()

roles = authorization.getGrantedRoles("globalRoles")

println '''
import hudson.*
import hudson.security.*
import jenkins.model.*
import java.util.*
import com.michelin.cio.hudson.plugins.rolestrategy.*
import java.lang.reflect.*
import java.util.logging.*
import groovy.json.*
'''
for (entry in roles) {
	role = entry.key
  	println 'globalRole_' + role.getName() + " = '" + role.getName() + "'"
}

println '\ndef access = ['
for (entry in roles) {
	role = entry.key
  	allUsers = ""
	users = entry.value
  	users.each { allUsers = allUsers + '"' + it + '",'}
    println role.getName() + " : [" + allUsers + "],"
}
println ']\n'


for (entry in roles) {
	role = entry.key
    println "def permissions_" + role.getName() + " = [" 
    role.permissions.each {
    	println '"' + it.id + '",'
    }
    println ']'
}

println '''
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
'''

for (entry in roles) {
	role = entry.key
    roleName = role.getName()

    println '''Set<Permission> ''' + roleName + '''PermissionSet = new HashSet<Permission>();
permissions_''' + role.getName() + '''.each { p ->
   def permission = Permission.fromId(p);
   if (permission != null) {
      ''' + roleName + '''PermissionSet.add(permission);
   } else {
      println("${p} is not a valid permission ID (ignoring)")
   }
}
'''
}

for (entry in roles) {
	role = entry.key
println 'Role ' + role.getName() + 'Role = new Role(globalRole_' + role.getName() + ', ' + role.getName() + 'PermissionSet);'
println 'roleBasedAuthenticationStrategy.addRole(RoleBasedAuthorizationStrategy.GLOBAL, ' + role.getName() + 'Role);\n'
}

for (entry in roles) {
	role = entry.key
println '''access.''' + role.getName() + '''.each { l ->
  println("Granting ''' + role.getName() + ''' to ${l}")
  roleBasedAuthenticationStrategy.assignRole(RoleBasedAuthorizationStrategy.GLOBAL, ''' + role.getName() + '''Role, l);  
}'''

}
