//======================================
//The Job Configuration History Plugin
//is needed in order for this script to 
//gather any information
//======================================

import jenkins.*
import jenkins.model.*
import hudson.*
import hudson.model.*
import groovy.json.JsonSlurper

def jenkinsURL = 'http://admin:173ae7c796a3f0b310f985a2bdfd85c3@localhost:8080' // URL should include API Token to work
def history = "" // Initiate history variable
def items = Jenkins.instance.getAllItems() // Get all of jenkins items

items.each { item ->
    // Create REST API command
    RestAPICommand = "curl -XGET ${jenkinsURL}/${item.url}/jobConfigHistory/api/json?pretty"
    // Execute command
    history = RestAPICommand.execute().text

    // Create a groovy object from retrieved JSON string
    def jsonObj = new JsonSlurper().parseText("${history}")

    //Assign newly created object to a variable
    history = jsonObj.jobConfigHistory

    //Go through each element in the history
    history.each { event ->
        // Look for creation event
        if (event.operation == "Created") {
            created = event.date.split("_") // Split date from time of creation
            dayCreated = created[0].replace("-", "/") // Assign date to variable and change format
            timeCreated = created[1].replace("-", ":") // Assign time to variable and change format
            println("${item.name},${dayCreated},${timeCreated}") // Print in CSV format jobName,creationDate,creationTime
        }
    }
}

println ""
