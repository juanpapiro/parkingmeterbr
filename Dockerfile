FROM openjdk:17-alpine

ADD target/parkingmeterbr-0.0.1.jar users.jar

ENTRYPOINT [ "java", "-jar",  "/parkingmeterbr.jar"]

EXPOSE 8001