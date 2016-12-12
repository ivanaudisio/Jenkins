nodes = Jenkins.instance.getNodes()

nodes.each { node ->
  println(node.displayName)
}
