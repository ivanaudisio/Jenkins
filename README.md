# Jenkins

### Get class methods and parameters

```
Hudson.instance.items.each { job ->

// Prints all the parameters from the object
job.metaClass.properties.each {println it.name}

// Prints all the methods from the object
println("Methods: " + job.metaClass.methods*.name.sort().unique())

}
```
