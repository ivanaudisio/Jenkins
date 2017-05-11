import jenkins.model.*
import hudson.model.*
import hudson.slaves.*
Jenkins.instance.addNode(new DumbSlave("{{ ansible_hostname }}-shared","","{{ remote_root_dir }}","{{ nb_slave_executors }}",Node.Mode.NORMAL,"{{ label_string }}",new JNLPLauncher(),new RetentionStrategy.Always(),new LinkedList()))
