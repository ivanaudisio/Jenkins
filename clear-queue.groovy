import hudson.model.*  
def q = jenkins.model.Jenkins.getInstance().getQueue()   
def items = q.getItems()  
  for (i=0;i<items.length;i++){  
    println items[i].task.getName()
    if(items[i].task.getName() == "NAME_OF_THE_JOB_TO_CANCEL"){  
      items[i].doCancelQueue()  
    }   
  }   
}
