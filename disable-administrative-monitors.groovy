import jenkins.model.*
import jenkins.*

def instance = Jenkins.getInstance()

// Disable all administrative Monitors from showing
def administrativeMonitors = instance.getActiveAdministrativeMonitors()

administrativeMonitors.each { monitor ->
  monitor.disable(true)
}
