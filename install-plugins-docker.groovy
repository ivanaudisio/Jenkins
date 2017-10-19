// Under the docker file add the following lines
// COPY plugins.txt /usr/share/jenkins/ref/
// RUN /usr/local/bin/plugins.sh /usr/share/jenkins/ref/plugins.txt
// Create a plugins.txt file with the list produced by this script

Jenkins.instance.pluginManager.plugins.each { plugin ->
  println("${plugin.shortName}:${plugin.version}")
}
