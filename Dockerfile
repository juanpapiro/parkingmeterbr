FROM openjdk:17-alpine

ADD target/parkingmeterbr-0.0.1.jar parkingmeterbr.jar

ENTRYPOINT [ "java", "-jar",  "/parkingmeterbr.jar"]

EXPOSE 8001