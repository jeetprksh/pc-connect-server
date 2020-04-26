FROM openjdk:8
ADD target/pc-connect-server-0.0.1-SNAPSHOT.jar pc-connect-server-0.0.1.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "pc-connect-server-0.0.1.jar"]
