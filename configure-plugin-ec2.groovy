import hudson.model.*
import jenkins.model.*
import hudson.plugins.ec2.*
import com.amazonaws.services.ec2.model.*

// http://www.pugme.co.uk/index.php/2017/07/07/automating-the-jenkins-ec2-plugin-using-groovy/
   
// Declare tags for Slave Templates 
def wordpressSlaveTags = []
wordpressSlaveTags.add(new EC2Tag("Name","Pugme Example"))
wordpressSlaveTags.add(new EC2Tag("SystemCode","Jenkins"))

// Not using spotConfig so set to null
SpotConfiguration spotConfig = null
AMITypeData amiType = null

/* Methods are overloaded for backwards compatibility 
   Get your SlaveTemplate Constructor to match the version 
   you desire. I've commented each field for your benefit */
SlaveTemplate wordpressTemplate = new SlaveTemplate(
// Slave AMI
  '{{ slave_ami }}',
// Zone
  '',
// SpotConfiguration SpotConfig
  spotConfig,
// Security Group Names
  '{{ slave_sg_names }}',
// Remote FS
  '/home/jenkins',
// Instance Type
  InstanceType.T2Small,
// EBS Optimised (boolean)
  false,
// Labels
  'Wordpress',
// Mode 
  Node.Mode.NORMAL,
// Description 
  'Wordpress',
// InitScript 
  '',
// Tmp Dir
  '',
// User Data
  '',
// NumExecutors
  '1',
// Remote Admin
  'jenkins',
// AMI Type 
  amiType,
// JVM opts
  '',
// Stop On Terminate (boolean)
  false,
// SubnetID
  '{{ slave_subnet_id }}',
// List of tags (List)
  wordpressSlaveTags,
// Idle Termination Minutes
  '30',
// Use Private DNS Name (boolean)
  false,
// Instance Cap 
  '1',
// IAM Instance Profile 
  '{{ slave_iam_instance_profile }}',
// Use Ephemeral Devices (boolean)
  true,
// Use Dedicated Tenancy (boolean) 
  false,
// Launch Timeout 
  '60',
// Associate Public IP
  false,
// CustomDeviceMapping
  '',
// Connect By SSH Process
  true
)

def slaveTemplates = [wordpressTemplate]

def ec2Cloud = new AmazonEC2Cloud(
  // CloudName
  'Pugme {{ infra_env }}',
  // Use Instance Profile For Credentials
  true,
  // CredentialsId
  '',
  // Region
  'eu-west-1',
  /* Put your private key in an Ansible Vault.
     EC2 Plugin expects escaped newlines so use Ansible regex filter 
     to do the magic */
  '{{ env_secrets.jenkins_ec2_key|regex_replace('\n', '\\n') }}',
  // Instance Cap String
  '2',
  // Slave Templates (List)
  slaveTemplates
)

def cloudList = Jenkins.instance.clouds
cloudList.add(ec2Cloud)
