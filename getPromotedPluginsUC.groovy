// This script gathers all available plugins in a give Update Center
import groovy.json.*
import java.util.SortedMap
import com.cloudbees.plugins.updatecenter.UpdateCenter
import com.cloudbees.plugins.updatecenter.PluginData
import com.cloudbees.jenkins.updates.versioning.VersionNumber
import com.cloudbees.jenkins.updates.data.PluginEntry
import com.cloudbees.opscenter.server.model.*
import com.cloudbees.opscenter.server.clusterops.steps.*

def jenkinsUpdateCenter = ""
UpdateCenter uc = Jenkins.instance.getItem(jenkinsUpdateCenter)
def plugins = []

uc.getPluginsData().each { plugin ->
  pluginName = plugin.name.toString()
  promoted = plugin.promotedVersion
  if (promoted) {
    plugins.add(pluginName)
  }
}

// Return result in JSON
print JsonOutput.toJson(["plugins": plugins])
