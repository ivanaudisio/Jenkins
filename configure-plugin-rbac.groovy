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

def globalRoleRead = "read"
def globalBuildRole = "build"
def globalRoleAdmin = "admin"

//* ===================================      
//*           Users and Groups
//* ===================================

// Users need to be previously created

def access = [
  admins: ["anonymous","user1"],
  builders: ["user2"],
  readers: ["user3"]
]

//* ===================================         
//*           Permissions
//* ===================================

def adminPermissions = [
"hudson.model.Hudson.Administer",
"hudson.model.Hudson.Read",
"com.cloudbees.plugins.credentials.CredentialsProvider.Create",
"com.cloudbees.plugins.credentials.CredentialsProvider.Delete",
"com.cloudbees.plugins.credentials.CredentialsProvider.ManageDomains",
"com.cloudbees.plugins.credentials.CredentialsProvider.Update",
"com.cloudbees.plugins.credentials.CredentialsProvider.View",
"hudson.model.Computer.Build",
"hudson.model.Computer.Configure",  
"hudson.model.Computer.Connect",
"hudson.model.Computer.Create",  
"hudson.model.Computer.Delete",  
"hudson.model.Computer.Disconnect",
"hudson.model.Computer.Provision",  
"hudson.model.Item.Build",
"hudson.model.Item.Cancel",
"hudson.model.Item.Configure",
"hudson.model.Item.Create",
"hudson.model.Item.Delete",
"hudson.model.Item.Discover",
"hudson.model.Item.Move",
"hudson.model.Item.Read",
"hudson.model.Item.Workspace",
"hudson.model.Run.Delete",
"hudson.model.Run.Replay",
"hudson.model.Run.Update",
"hudson.model.View.Configure",
"hudson.model.View.Create",
"hudson.model.View.Delete",
"hudson.model.View.Read",
"hudson.scm.SCM.Tag",
]

def readPermissions = [
"hudson.model.Hudson.Read",
"hudson.model.Item.Discover",
"hudson.model.Item.Read"
]

def buildPermissions = [
"hudson.model.Hudson.Read",
"hudson.model.Item.Build",
"hudson.model.Item.Cancel",
"hudson.model.Item.Read",
"hudson.model.Run.Replay"
]

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

Set<Permission> adminPermissionSet = new HashSet<Permission>();
adminPermissions.each { p ->
  def permission = Permission.fromId(p);
  if (permission != null) {
    adminPermissionSet.add(permission);
  } else {
    println("${p} is not a valid permission ID (ignoring)")
  }
}

Set<Permission> buildPermissionSet = new HashSet<Permission>();
buildPermissions.each { p ->
  def permission = Permission.fromId(p);
  if (permission != null) {
    buildPermissionSet.add(permission);
  } else {
    println("${p} is not a valid permission ID (ignoring)")
  }
}

Set<Permission> readPermissionSet = new HashSet<Permission>();
readPermissions.each { p ->
  def permission = Permission.fromId(p);
  if (permission != null) {
    readPermissionSet.add(permission);
  } else {
    println("${p} is not a valid permission ID (ignoring)")
  }
}

//* ===================================
//*      Permissions -> Roles
//* ===================================

// admins
Role adminRole = new Role(globalRoleAdmin, adminPermissionSet);
roleBasedAuthenticationStrategy.addRole(RoleBasedAuthorizationStrategy.GLOBAL, adminRole);

// builders
Role buildersRole = new Role(globalBuildRole, buildPermissionSet);
roleBasedAuthenticationStrategy.addRole(RoleBasedAuthorizationStrategy.GLOBAL, buildersRole);

// anonymous read
Role readRole = new Role(globalRoleRead, readPermissionSet);
roleBasedAuthenticationStrategy.addRole(RoleBasedAuthorizationStrategy.GLOBAL, readRole);

//* ===================================      
//*      Roles -> Groups/Users
//* ===================================

access.admins.each { l ->
  println("Granting admin to ${l}")
  roleBasedAuthenticationStrategy.assignRole(RoleBasedAuthorizationStrategy.GLOBAL, adminRole, l);  
}

access.builders.each { l ->
  println("Granting builder to ${l}")
  roleBasedAuthenticationStrategy.assignRole(RoleBasedAuthorizationStrategy.GLOBAL, buildersRole, l);  
}

access.readers.each { l ->
  println("Granting read to ${l}")
  roleBasedAuthenticationStrategy.assignRole(RoleBasedAuthorizationStrategy.GLOBAL, readRole, l);  
}

Jenkins.instance.save()
