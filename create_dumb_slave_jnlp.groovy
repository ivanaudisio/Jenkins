import jenkins.model.*
import hudson.model.*
import hudson.slaves.*

Jenkins.instance.addNode(
  new DumbSlave(
    "test-script",
    "test slave description",
    "/export/home/pe-deploy/",
    "1",
    Node.Mode.NORMAL,
    "test-slave-label",
    new JNLPLauncher(),
    new RetentionStrategy.Always(),
    new LinkedList()))
