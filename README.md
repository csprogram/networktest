# A Simple Network Speed Test Program

This program will fetch the specify network resources every 5 minutes(default).

There two collection of resource: 
* src/main/resources/big.properties
* src/main/resources/small.properties

There is a pre-build executable package in the folder `dist`, you can download the files and run it directly.

Build the executable package
---------------------------
1. First make sure you installed java 1.7 or above (OpenJDK or Oracle JDK should be fine).
2. Install and configure Apache Maven.
3. Check out the source and execute ` $ mvn clean package`
4. It will generates file target/networktest-1.0-SNAPSHOT-jar-with-dependencies.jar

Run
---
Just type this command in console/terminal:

`$ java -jar YOUR-BUILDED.jar`
