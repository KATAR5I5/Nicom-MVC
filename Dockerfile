FROM openjdk

ADD /target/FirstSecurityApp-0.0.1-SNAPSHOT.jar backend.jar

ENTRYPOINT ["java", "-jar", "backend.jar"]