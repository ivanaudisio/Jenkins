println ("***** ANSIBLE WITH VERSION *****")

println ("jenkins_plugins:")
Jenkins.instance.pluginManager.plugins.each { plugin ->
  println("  - { name: ${plugin.shortName},version: ${plugin.version} }")
}

println ("""
# Installs plugins with specific version
- name: Install plugins with a specific version
  jenkins_plugin:
    name: "{{ item.name }}"
    version: "{{ item.version }}"
    url: "{{ jenkins_url }}"
    with_dependencies: yes
  register: my_jenkins_plugin_versioned
  when: >
    item.version is defined
  with_items: "{{ jenkins_plugins }}"
  become: true
""")
println ""
println ("***** ANSIBLE WITHOUT VERSION *****")
println ("jenkins_plugins:")
Jenkins.instance.pluginManager.plugins.each { plugin ->
  println("  - { name: ${plugin.shortName} }")
}
println ("""
# Installs plugins without version
- name: Install plugins without a specific version
  jenkins_plugin:
    name: "{{ item.name }}"
    url: "{{ jenkins_url }}"
  register: my_jenkins_plugin
  when: >
    item.version is not defined
  with_items: "{{ jenkins_plugins }}"
  become: true
""")
