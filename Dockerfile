FROM amazoncorretto:17 as build

COPY ./ /app 
WORKDIR /app
# COPY pom.xml .
RUN sed -i -E 's/^(spring\.datasource\.url=jdbc:mysql:\/\/)(.*)(:3306\/maindb)/\1db\3/'\
    ./src/main/resources/application.properties

RUN ./mvnw dependency:go-offline
# COPY src ./src
RUN ./mvnw package -DskipTests

FROM amazoncorretto:17
MAINTAINER 0812058@utp.edu.pe 
# COPY target/springbootdemo-latest.jar /springbootdemo-latest.jar
COPY --from=build /app/target/springbootdemo-latest.jar /springbootdemo-latest.jar
#COPY ./mvnw /
## ENTRYPOINT ["java","-jar","/springbootdemo-latest.jar"]
CMD ["java","-jar","/springbootdemo-latest.jar"]
# CMD ["/mvnw","spring-boot:run"]

