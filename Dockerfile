FROM amazoncorretto:17 as build

COPY ./ /app 
WORKDIR /app
# COPY pom.xml .
## RUN sed -i -E 's/^(spring\.datasource\.url=jdbc:mysql:\/\/)(.*)(:3306\/maindb)/\1db\3/'\
##     ./src/main/resources/application.properties

RUN ./mvnw dependency:go-offline
# COPY src ./src
RUN ./mvnw package -DskipTests

FROM amazoncorretto:17
MAINTAINER 0812058@utp.edu.pe 
# COPY target/springbootdemo-latest.jar /springbootdemo-latest.jar

### Docker Dev environment set up
# ENV SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal/sra_sprinboot_backend
ENV SPRING_DATASOURCE_URL=jdbc:mysql://db/maindb
## ENV SPRING_DATASOURCE_USERNAME=
## ENV SPRING_DATASOURCE_PASSWORD=
## ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.mysql.cj.jdbc.Driver
## ENV LOGGING_LEVEL_ORG_HIBERNATE_TYPE_DESCRIPTOR_SQL_BASIC_BINDER=TRACE

COPY --from=build /app/target/springbootdemo-latest.jar /springbootdemo-latest.jar
#COPY ./mvnw /
## ENTRYPOINT ["java","-jar","/springbootdemo-latest.jar"]
CMD ["java","-jar","/springbootdemo-latest.jar"]
# CMD ["/mvnw","spring-boot:run"]

