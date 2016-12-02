// author : Ivan Audisio
// list jobs not run in the last N days / last N months

import groovy.time.TimeCategory
import java.text.SimpleDateFormat;

// TO DO
// Add validation when entering stratTime and endTime (end needs to be higher)
// Add specific date (remember to update how header is shown)
// Able to set parameters to show in the CSV report
// validate startTime and endTime format

// ***************************************************
// CONFIGURATION
// ***************************************************
def days = 3				// Ammount of days to report (Including today)
def startTime = ""			// If left blank by default it starts at the beggining of the day 00:00:00 [24 hour format e.g. 06:20:00]
def endTime = ""			// If left blank by default it ends at the end of the day 23:59:00 [24 hour format e.g. 14:30:00]
//def specificDate = ""			// If a value is entered it overrides the ammount of days to search for (e.g. 28-11-2016)
def showTemplate = false
def showJobRootDir = false
def showJobURL = false
def showJobAbsoluteURL = false

// ***************************************************
// INITIALIZE DEFAULT PARAMETERS
// ***************************************************
if (!startTime) {startTime = "00:00:00"}
if (!endTime) {endTime = "23:59:50"}

// ***************************************************
// CREATE CSV HEADER
// ***************************************************
def headerString = "Job"					// Initialize header for CSV report
showTemplate ? headerString += ";Template":""			// Add Tempalte to header
showJobRootDir ? headerString += ";Job Root Dir":""		// Add Job URL to header
showJobURL ? headerString += ";Job URL":""			// Add Job Path to header
showJobAbsoluteURL ? headerString += ";Job Absolute URL":""	// Add Job Path to header
for (i = days; i > 0; i--) {					// Add days to header	
  header = new Date() - (i - 1)
  header = header.format( 'd-M-yyyy' )
  headerString += ";" + header
}
(days > 1) ? headerString += ";Total Builds":""			// Add Total Number of builds to header
println("${headerString}")					// Print header

// ***************************************************
// BEGING GATHERING JOBS INFORMATION FOR GIVEN DATES
// ***************************************************

def jobs = Jenkins.instance.getAllItems()			// Get all jenkins items

jobs.each {job ->

	// Verify that the item contains the method getLastBuild (avoids templates and folders) 
	if (job.metaClass.getMetaMethod("getLastBuild")) {

		template = ""
		buildString = ""
		totalBuilds = 0
		
		// Get Builds per day
		for (i = days; i > 0; i--) {
			count = 0									// Restart build count per Job
          
			startDate = new Date() - (i - 1)						// Set starting day
			startDate = startDate.format( 'd-M-yyyy' )					// Set date format
			startDate = Date.parse("d-M-yyyy HH:mm:ss", "${startDate} ${startTime}")	// Set starting time
			startDate = startDate.getTime()							// Get Timestamp for the formed date

			endDate = new Date() - (i - 1)							// Set ending day
			endDate = endDate.format( 'd-M-yyyy' )						// Set date format
			endDate = Date.parse("d-M-yyyy HH:mm:ss", "${endDate} ${endTime}")       	// Set ending time   
			endDate =  endDate.getTime();  							// Get Timestamp for the formed date
          
			builds = job.getBuilds().byTimestamp(startDate,endDate)				// Get all builds with both timestamps
			builds.each{build -> count ++}							// Count number of builds found
			buildString += ";${count}"
			totalBuilds += count			
		}
		
		// Valite if job is an instance of a template
		if (showTemplate) {
			def model = com.cloudbees.hudson.plugins.modeling.impl.entity.EntityInstance.from(job)
			if (model) {
				template = "${model.modelId}" // Get template path
			}
		}

		// Print requested Job information in one row (CSV format)
		newRow = "${job.name}"						// Add Job name
		showTemplate ? newRow += ";${template}":""			// Add Job template
		showJobRootDir ? newRow += ";${job.rootDir}":""			// Add Job root directory
		showJobURL ? newRow += ";${job.url}":""				// Add Job url
		showJobAbsoluteURL ? newRow += ";${job.absoluteUrl}":""		// Add Job absolute url
		newRow += "${buildString}"					// Add days
		(days > 1) ? newRow += ";${totalBuilds}":""			// Add total builds in given days
		println("${newRow}")						// Print all parameters
	}
}
println("")
