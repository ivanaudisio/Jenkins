// Imports
import com.cloudbees.opscenter.server.model.ClientMaster
import com.cloudbees.opscenter.server.model.ConnectedMaster
import com.cloudbees.opscenter.server.model.OperationsCenter
import com.cloudbees.opscenter.server.properties.ConnectedMasterLicenseServerProperty
import jenkins.model.Jenkins

// Reqs

// Further information
//  The JENKINS_JAVA_OPTIONS parameters needed to be run in the Jenkins Sysconfig file are pre-populated
//  in the installation of the Jenkins Service. If these parameters are left blank
//  Jenkins will still function as normal, and java will not consider those
//  The list of parameters are:
// -DMASTER_ENDPOINT
// -DMASTER_OPERATIONSCENTER_ENDPOINT
// -DMASTER_INDEX
// -DMASTER_NAME
// -DMASTER_GRANT_ID

// Input parameters
def clientMasterName = "my_client_master"
def clientMasterId = 1123
def clientMasterGrantId = "GrantID"
def clientMasterLicenseStrategy = new ConnectedMasterLicenseServerProperty.FloatingExecutorsStrategy() // or  ConnectedMasterLicenseServerProperty.NoLicensingStrategy();

// Create Client Master Declaration
ClientMaster cm = OperationsCenter.instance.createClientMaster(clientMasterName)
cm.setId(clientMasterId)
cm.setIdName(ConnectedMaster.createIdName(clientMasterId, clientMasterName))
cm.setGrantId(clientMasterGrantId)
cm.getProperties().replace(new ConnectedMasterLicenseServerProperty(clientMasterLicenseStrategy))
cm.save()

//Validate the CM has been created in CJOC
if (OperationsCenter.instance.getConnectedMasterByName(cm.idName)!=null){
  println "[INFO:]" + cm.idName + " has been created in CJOC"
} else {
  println "[ERROR:]" + cm.idName + "has not been created in CJOC"
}
