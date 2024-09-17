FROM ringcentral/maven:3.8.2-jdk17 AS build

COPY . .

RUN mvn clean package


FROM openjdk:17-jdk

COPY --from=build /target/api-nextgen-0.0.1-SNAPSHOT.jar /opt/api-nextgen.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/opt/api-nextgen.jar"]
