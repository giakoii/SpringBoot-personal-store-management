FROM maven:3.8.1-openjdk-17-slim AS build
#VOLUME /tmp
#COPY target/*.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
#EXPOSE 2003

COPY . .
RUN mvn clean package -DskipTests
# List contents of /target to verify the JAR file's presence
RUN ls -la target/

FROM openjdk:17-jdk-slim
# Ensure the JAR file is copied correctly. Adjust the source path if necessary.
COPY --from=build /target/personal-store-management-project-0.0.1-SNAPSHOT.jar personal-store-management.jar
EXPOSE 2003
ENTRYPOINT ["java", "-jar", "personal-store-management.jar"]