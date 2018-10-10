# User Manual


### PreCondition
* JDK 1.8 
* mysql 5.6 
* nginx (for centos)

### Operation
1. local
  * execute migration script to init db
  * import project in IDEA
  * RUN
  * verify by typing in Browser http://localhost:8080/misc/version

2. CentOS
  * execute migration script to init db
  * copy jar package to desired machine
  * java -jar walle-0.1.0.jar
  * verify by typing in Browser http://{domain}:8080/misc/version
