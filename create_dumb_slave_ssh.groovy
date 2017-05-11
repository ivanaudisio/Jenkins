import hudson.model.Node.Mode
import hudson.slaves.*
import jenkins.model.Jenkins
import hudson.plugins.sshslaves.SSHLauncher

// Other parameters are just hard coded for now, adjust as needed
String agentHome = ''
String agentExecutors = ''
String agentLabels = ''
String agentName = ''
String agentDescription = ''
String agentHostname = ''
String credentialID = ''

// There is a constructor that also takes a list of properties (env vars) at the end, but haven't needed that yet
DumbSlave dumb = new DumbSlave(agentName, // Agent name, usually matches the host computer's machine name
        agentDescription, // Agent description
        agentHome, // Workspace on the agent's computer
        agentExecutors, // Number of executors
        Mode.EXCLUSIVE, // "Usage" field, EXCLUSIVE is "only tied to node", NORMAL is "any"
        agentLabels, // Labels
        new SSHLauncher(agentHostname, 22, SSHLauncher.lookupSystemCredentials(credentialID), "", null, null, "", "", 60, 3, 15),
        RetentionStrategy.INSTANCE) // Is the "Availability" field and INSTANCE means "Always"
Jenkins.instance.addNode(dumb)
