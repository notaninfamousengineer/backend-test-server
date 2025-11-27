FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/firsttryout-0.0.1-SNAPSHOT.jar app.jar

ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["java", "-jar", "app.jar"]