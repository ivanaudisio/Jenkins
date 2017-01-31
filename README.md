# Jenkins

### To Do

General report with the following information:

1) Master URL  
2) Master OS  
3) Jenkins instance ID  
4) Master version  
5) OS memory  
6) Master number of executors  
7) Slaves and number of executors  
8) CPU cores    
9) Java Version  
10) Heap size  
11) Number of Jobs
    - % of pipeline jobs
    - % of Maven jobs
    - % of freestyle jobs  
12) Empty folders  
13) Jobs utilizing pooling and CRON  
14) Jobs to be updated (Always recomend to upgrade to latest after a week of release)  
15) Retention policy (folder size)
16) timeout policies 

Notes
* Schedule Regular Restarts During Quiet Hours (Restart every 2 weeks is always recomended)
* Monitor memory use
* Set bot initial and maximum heap size
* Add ‘%t’ to the GC log location (check this)

### Get class methods and parameters

```
Hudson.instance.items.each { job ->

// Prints all the parameters from the object
job.metaClass.properties.each {println it.name}

// Prints all the methods from the object
println("Methods: " + job.metaClass.methods*.name.sort().unique())

}

// Prints all of jenkins internal configurations
def instance = Jenkins.getInstance()
instance.metaClass.properties.each {println it.name}
```
