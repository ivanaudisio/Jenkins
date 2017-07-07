// Imports
import com.cloudbees.opscenter.plugins.*
import jenkins.*

// Get the target client master to configure its UC
def master = Jenkins.instance.getItemByFullName("my_client_master")

println master.getProperties().remove(com.cloudbees.opscenter.plugins.updatecenter.ConnectedMasterUpdateCenterProperty.class)
// println master.getProperties()

master.getProperties().add(new com.cloudbees.opscenter.plugins.updatecenter.ConnectedMasterUpdateCenterProperty(
  new com.cloudbees.plugins.updatecenter.sources.LocalUpdateSource("http://www.my_jenkins.example.com:8888")))

// Check the applied configuration
// println master.getProperties()

// Save the changes
master.save()
