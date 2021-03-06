Hudson.instance.items.each { job ->

	//job.metaClass.properties.each {println it.name}
	
	//Jobs
	if (job instanceof org.jenkinsci.plugins.workflow.job.WorkflowJob) {
	println("Name: ${job.name}")
	println("Class: ${job.class}")
	println("")
	println("Methods: " + job.metaClass.methods*.name.sort().unique())
	println("")
	println("parameterized: ${job.parameterized}")
	println("rootDir: ${job.rootDir}")
	println("logUpdated: ${job.logUpdated}")
	println("searchIndex: ${job.searchIndex}")
	println("properties: ${job.properties}")
	println("allJobs: ${job.allJobs}")
	println("SCMs: ${job.SCMs}")
	println("overrides: ${job.overrides}")
	println("ACL: ${job.ACL}")
	println("descriptor: ${job.descriptor}")
	println("lastSuccessfulBuild: ${job.lastSuccessfulBuild}")
	println("estimatedDurationCandidates: ${job.estimatedDurationCandidates}")
	println("buildDir: ${job.buildDir}")
	println("search: ${job.search}")
	println("actions: ${job.actions}")
	println("buildNowText: ${job.buildNowText}")
	println("SCMTrigger: ${job.SCMTrigger}")
	println("configFile: ${job.configFile}")
	println("buildDiscarder: ${job.buildDiscarder}")
	println("fullName: ${job.fullName}")
	println("displayName: ${job.displayName}")
	println("fullDisplayName: ${job.fullDisplayName}")
	println("searchName: ${job.searchName}")
	println("quietPeriod: ${job.quietPeriod}")
	println("resourceList: ${job.resourceList}")
	println("subTasks: ${job.subTasks}")
	println("absoluteUrl: ${job.absoluteUrl}")
	println("whyBlocked: ${job.whyBlocked}")
	println("lazyBuildMixIn: ${job.lazyBuildMixIn}")
	println("hasCustomQuietPeriod: ${job.hasCustomQuietPeriod}")
	println("buildable: ${job.buildable}")
	println("url: ${job.url}")
	println("lastStableBuild: ${job.lastStableBuild}")
	println("ownerTask: ${job.ownerTask}")
	println("api: ${job.api}")
	println("searchUrl: ${job.searchUrl}")
	println("definition: ${job.definition}")
	println("iconColor: ${job.iconColor}")
	println("triggers: ${job.triggers}")
	println("pronoun: ${job.pronoun}")
	println("displayNameOrNull: ${job.displayNameOrNull}")
	println("authToken: ${job.authToken}")
	println("assignedLabel: ${job.assignedLabel}")
	println("parent: ${job.parent}")
	println("nameEditable: ${job.nameEditable}")
	println("builds: ${job.builds}")
	println("queueItem: ${job.queueItem}")
	println("timeline: ${job.timeline}")
	println("buildHealthReports: ${job.buildHealthReports}")
	println("inQueue: ${job.inQueue}")
	println("lastFailedBuild: ${job.lastFailedBuild}")
	println("keepDependencies: ${job.keepDependencies}")
	println("sameNodeConstraint: ${job.sameNodeConstraint}")
	println("holdOffBuildUntilSave: ${job.holdOffBuildUntilSave}")
	println("typicalSCM: ${job.typicalSCM}")
	println("nextBuildNumber: ${job.nextBuildNumber}")
	println("allProperties: ${job.allProperties}")
	println("class: ${job.class}")
	println("lastBuild: ${job.lastBuild}")
	println("lastBuiltOn: ${job.lastBuiltOn}")
	println("buildStatusUrl: ${job.buildStatusUrl}")
	println("buildHealth: ${job.buildHealth}")
	println("buildsAsMap: ${job.buildsAsMap}")
	println("firstBuild: ${job.firstBuild}")
	println("buildTimeGraph: ${job.buildTimeGraph}")
	println("permalinks: ${job.permalinks}")
	println("causeOfBlockage: ${job.causeOfBlockage}")
	println("shortUrl: ${job.shortUrl}")
	println("building: ${job.building}")
	println("logRotator: ${job.logRotator}")
	println("lastUnstableBuild: ${job.lastUnstableBuild}")
	println("widgets: ${job.widgets}")
	println("buildBlocked: ${job.buildBlocked}")
	println("lastCompletedBuild: ${job.lastCompletedBuild}")
	println("concurrentBuild: ${job.concurrentBuild}")
	println("lastUnsuccessfulBuild: ${job.lastUnsuccessfulBuild}")
	println("nextBuildNumberFile: ${job.nextBuildNumberFile}")
	println("allActions: ${job.allActions}")
	println("description: ${job.description}")
	println("newBuilds: ${job.newBuilds}")
	println("buildStatusIconClassName: ${job.buildStatusIconClassName}")
	println("estimatedDuration: ${job.estimatedDuration}")
	println("characteristicEnvVars: ${job.characteristicEnvVars}")
	println("defaultAuthentication: ${job.defaultAuthentication}")
	}
	
	//Folders
	if (job instanceof com.cloudbees.hudson.plugins.folder.Folder) {
	println("Name: ${job.name}")
	println("Class: ${job.class}")
	println("")
	println("Methods: " + job.metaClass.methods*.name.sort().unique())
	println("")
	println("rootDir: ${job.rootDir}")
	println("urlChildPrefix: ${job.urlChildPrefix}")
	println("buildHealthReports: ${job.buildHealthReports}")
	println("searchIndex: ${job.searchIndex}")
	println("columns: ${job.columns}")
	println("viewsTabBar: ${job.viewsTabBar}")
	println("properties: ${job.properties}")
	println("allJobs: ${job.allJobs}")
	println("overrides: ${job.overrides}")
	println("ACL: ${job.ACL}")
	println("descriptor: ${job.descriptor}")
	println("search: ${job.search}")
	println("healthMetrics: ${job.healthMetrics}")
	println("class: ${job.class}")
	println("actions: ${job.actions}")
	println("itemDescriptors: ${job.itemDescriptors}")
	println("successfulDestination: ${job.successfulDestination}")
	println("buildHealth: ${job.buildHealth}")
	println("configFile: ${job.configFile}")
	println("primaryView: ${job.primaryView}")
	println("fullName: ${job.fullName}")
	println("displayName: ${job.displayName}")
	println("fullDisplayName: ${job.fullDisplayName}")
	println("searchName: ${job.searchName}")
	println("newPronoun: ${job.newPronoun}")
	println("absoluteUrl: ${job.absoluteUrl}")
	println("jobsDir: ${job.jobsDir}")
	println("shortUrl: ${job.shortUrl}")
	println("items: ${job.items}")
	println("icon: ${job.icon}")
	println("url: ${job.url}")
	println("api: ${job.api}")
	println("searchUrl: ${job.searchUrl}")
	println("views: ${job.views}")
	println("iconColor: ${job.iconColor}")
	println("pronoun: ${job.pronoun}")
	println("itemGroup: ${job.itemGroup}")
	println("displayNameOrNull: ${job.displayNameOrNull}")
	println("description: ${job.description}")
	println("allActions: ${job.allActions}")
	println("staplerFallback: ${job.staplerFallback}")
	println("viewActions: ${job.viewActions}")
	println("parent: ${job.parent}")
	}
	
	//Template
	if (job instanceof com.cloudbees.hudson.plugins.modeling.impl.jobTemplate.JobTemplate) {
	println("Name: ${job.name}")
	println("Class: ${job.class}")
	println("")
	println("Methods: " + job.metaClass.methods*.name.sort().unique())
	println("")
	println("rootDir: ${job.rootDir}")
	println("superTypeId: ${job.superTypeId}")
	println("searchIndex: ${job.searchIndex}")
	println("properties: ${job.properties}")
	println("id: ${job.id}")
	println("allJobs: ${job.allJobs}")
	println("help: ${job.help}")
	println("ACL: ${job.ACL}")
	println("descriptor: ${job.descriptor}")
	println("itemClass: ${job.itemClass}")
	println("search: ${job.search}")
	println("superType: ${job.superType}")
	println("class: ${job.class}")
	println("actions: ${job.actions}")
	println("directSubTypes: ${job.directSubTypes}")
	println("configFile: ${job.configFile}")
	println("allAttributes: ${job.allAttributes}")
	println("fullName: ${job.fullName}")
	println("displayName: ${job.displayName}")
	println("fullDisplayName: ${job.fullDisplayName}")
	println("searchName: ${job.searchName}")
	println("absoluteUrl: ${job.absoluteUrl}")
	println("transformer: ${job.transformer}")
	println("shortUrl: ${job.shortUrl}")
	println("attributes: ${job.attributes}")
	println("extensionList: ${job.extensionList}")
	println("url: ${job.url}")
	println("api: ${job.api}")
	println("searchUrl: ${job.searchUrl}")
	println("iconColor: ${job.iconColor}")
	println("relativeDisplayName: ${job.relativeDisplayName}")
	println("pronoun: ${job.pronoun}")
	println("displayNameOrNull: ${job.displayNameOrNull}")
	println("allActions: ${job.allActions}")
	println("description: ${job.description}")
	println("instantiable: ${job.instantiable}")
	println("parent: ${job.parent}")
	}
	//println job.toString()

    println("")
    //println("Methods		: " + job.metaClass.methods*.name.sort().unique())
    println("")
    
    println("")
    println("")

}
