# A Simple Network Speed Test Program

This program will fetch the specify network resources every 5 minutes (default) and record the spend time.

The specify resources were defined in these two files: 
* src/main/resources/big.properties
* src/main/resources/small.properties

There is a pre-build executable package in the folder `dist`, you can download the files and run it directly.

Build the executable package
---------------------------
1. First make sure you have installed java 1.7 or above (OpenJDK or Oracle JDK would be fine).
2. Install and configure Apache Maven.
3. Check out the source and execute ` $ mvn clean package`.
4. It will generates a file `target/networktest-1.0-SNAPSHOT-jar-with-dependencies.jar`.

Run
---
In Linux or OSX just type this command in the console/terminal:

`$ java -jar YOUR-BUILDED.jar`

In Windows you can double-click the builded file in the file explorer.
