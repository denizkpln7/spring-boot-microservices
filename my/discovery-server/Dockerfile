FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} discoveryserver.jar

ENTRYPOINT ["java", "-jar", "/discoveryserver.jar"]

EXPOSE 8761