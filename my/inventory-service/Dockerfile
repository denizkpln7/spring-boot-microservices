FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} inventoryserver.jar

ENTRYPOINT ["java", "-jar", "/inventoryserver.jar"]

EXPOSE 8082