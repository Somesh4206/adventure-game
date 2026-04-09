@echo off
set JAVA_HOME=C:\Program Files\Microsoft\jdk-17.0.16.8-hotspot
set PATH=C:\Program Files\apache-maven-3.9.14\bin;%PATH%
cd backend
mvn spring-boot:run
