FROM openjdk:17-alpine

ARG JAR_FILE=target/*.jar

ARG CONFIG_FILE_LOCATION=src/main/resources/*

COPY ${CONFIG_FILE_LOCATION} /config/

COPY ${JAR_FILE} /banking-app.jar

EXPOSE 8099

CMD ["java", "-Dspring.config.location=/config/", "-jar", "banking-app.jar"]