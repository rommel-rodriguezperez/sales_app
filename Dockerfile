FROM amazoncorretto:17 as build

COPY ./ /app 
WORKDIR /app
# COPY pom.xml .
RUN ./mvnw dependency:go-offline
# COPY src ./src
RUN ./mvnw package

FROM amazoncorretto:17
MAINTAINER 0812058@utp.edu.pe 
# COPY target/springbootdemo-latest.jar /springbootdemo-latest.jar
COPY --from=build /app/target/springbootdemo-latest.jar /springbootdemo-latest.jar
#COPY ./mvnw /
## ENTRYPOINT ["java","-jar","/springbootdemo-latest.jar"]
CMD ["java","-jar","/springbootdemo-latest.jar"]
# CMD ["/mvnw","spring-boot:run"]

