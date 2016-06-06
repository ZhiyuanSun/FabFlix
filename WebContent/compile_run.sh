 cd /var/lib/tomcat7/webapps/fabflix/src
 sudo javac -classpath ../mysql-connector-java-5.1.34-bin.jar:. StartParsing.java
 java -classpath ../mysql-connector-java-5.1.34-bin.jar:. StartParsing
 
 #If you want to delete data but keep the table in database, call stored procedure "abc"