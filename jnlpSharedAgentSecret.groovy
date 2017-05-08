Jenkins.instance.allItems.grep {
    // Selecting Share Slave
    it.class.name == 'com.cloudbees.opscenter.server.model.SharedSlave'
}.each {
    // Selecting JocJnlpSlaveLauncher
   if (it.launcher.class.name == 'com.cloudbees.opscenter.server.jnlp.slave.JocJnlpSlaveLauncher'){
     println "--------------"
     println "Name:" + it.name
     println "Secret:" + it.launcher.getJnlpMac(it)
     println "--------------"
   }  
}
