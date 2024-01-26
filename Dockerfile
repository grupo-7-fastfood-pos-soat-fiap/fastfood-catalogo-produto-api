FROM openjdk:17

COPY . target/*.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]