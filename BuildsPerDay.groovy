// author : Ivan Audisi
// list jobs not run in the last N days
import groovy.time.TimeCategory
import java.text.SimpleDateFormat;
import jenkins.*
import jenkins.model.*
import hudson.*
import hudson.model.* 
	
// ***************************************************
// CONFIGURATION
// ***************************************************
def days = 10 // Ammount of days to report beginning from 'startingDate'. If 'startingDate' is not specified it reports the last number of days starting from today's date
def startingDate = "" // Change the starting date for the report with [FORMAT: dd-mm-yyy] (e.g. 2-12-2016)
def startTime = "" // Sets the time of the day to begin counting builds per job  [FORMAT: mm-ss-ms] (e.g. 06:20:00) If blank, it defaults to 00:00:00
def endTime = "" // Sets the time of the day to end counting builds per job  [FORMAT: mm-ss-ms] (e.g. 18:20:00) If blank, it defaults to 23:59:59
def showTotal = false // Show total number of builds per job when days are greater than 1
def showTemplate = false // Show job template
def showJobRootDir = false // Show job Root Directory
def showJobURL = false // Show job URL
def showJobAbsoluteURL = false // Show job abosolute URL

// ***************************************************
// INITIALIZE DEFAULT PARAMETERS
// ***************************************************
if (!startTime) {
    startTime = "00:00:00"
}
if (!endTime) {
    endTime = "23:59:59"
}

// ***************************************************
// CREATE CSV HEADER
// ***************************************************
def headerString = "Job" // Initialize header for CSV report
showTemplate ? headerString += ";Template" : "" // Add Tempalte to header
showJobRootDir ? headerString += ";Job Root Dir" : "" // Add Job URL to header
showJobURL ? headerString += ";Job URL" : "" // Add Job Path to header
showJobAbsoluteURL ? headerString += ";Job Absolute URL" : "" // Add Job Path to header
for (i = days; i > 0; i--) { // Add days to header	
    if (startingDate) {
        header = new Date()
        header = header.parse('d-M-yyyy', "$startingDate") + (days) - i  // Parse given date
        header = header.format('d-M-yyyy') // Set date format
    } else {
        header = new Date() - (i - 1) // Get today's date
        header = header.format('d-M-yyyy') // Set date format
    }
    headerString += ";" + header
}
(days > 1 && showTotal) ? headerString += ";Total Builds": "" // Add Total Number of builds to header
println("${headerString}") // Print header

// ***************************************************
// BEGING GATHERING JOBS INFORMATION FOR GIVEN DATES
// ***************************************************

def jobs = Jenkins.instance.getAllItems() // Get all jenkins items

jobs.each { job ->

	// Verify that the item contains the method getLastBuild (avoids templates and folders) 
	if (job.metaClass.getMetaMethod("getLastBuild")) {

		template = ""
		buildString = ""
		totalBuilds = 0

		// Get Builds per day

		for (i = days; i > 0; i--) {
			count = 0 // Restart build count per Job

			if (startingDate) {
				startDate = new Date()
				startDate = startDate.parse('d-M-yyyy', "$startingDate") + (days) - i  // Set date format
			} else {
				startDate = new Date() - (i - 1) // Set starting day
			}
			startDate = startDate.format('d-M-yyyy') // Set date format
			startDate = Date.parse("d-M-yyyy HH:mm:ss", "${startDate} ${startTime}") // Set starting time
			startDate = startDate.getTime() // Get Timestamp for the formed date

			if (startingDate) {
				endDate = new Date()
				endDate = endDate.parse('d-M-yyyy', "$startingDate") + (days) - i 
			} else {
				endDate = new Date() - (i - 1) // Set ending day
			}
			endDate = endDate.format('d-M-yyyy') // Set date format
			endDate = Date.parse("d-M-yyyy HH:mm:ss", "${endDate} ${endTime}") // Set ending time   
			endDate = endDate.getTime(); // Get Timestamp for the formed date

			builds = job.getBuilds().byTimestamp(startDate, endDate) // Get all builds with both timestamps
			builds.each { build -> count++ } // Count number of builds found
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
		newRow = "${job.name}" // Add Job name
		showTemplate ? newRow += ";${template}" : "" // Add Job template
		showJobRootDir ? newRow += ";${job.rootDir}" : "" // Add Job root directory
		showJobURL ? newRow += ";${job.url}" : "" // Add Job url
		showJobAbsoluteURL ? newRow += ";${job.absoluteUrl}" : "" // Add Job absolute url
		newRow += "${buildString}" // Add days
			(days > 1 && showTotal) ? newRow += ";${totalBuilds}" : "" // Add total builds in given days
		println("${newRow}") // Print all parameters
	}
}
println("")
