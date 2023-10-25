FROM openjdk:8-jdk-alpine
MAINTAINER 0812058@utp.edu.pe 
COPY target/docker-message-server-1.0.0.jar message-server-1.0.0.jar
ENTRYPOINT ["java","-jar","/message-server-1.0.0.jar"]

