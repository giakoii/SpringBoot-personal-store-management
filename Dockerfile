FROM maven:3.8.1-openjdk-17-slim AS build
COPY . .
RUN mvn clean package -DskipTests
# List contents of /target to verify the JAR file's presence
RUN ls -la target/

FROM openjdk:17-jdk-slim
# Ensure the JAR file is copied correctly. Adjust the source path if necessary.
COPY --from=build /target/personal-store-management-1.0.jar /app/app.jar
EXPOSE 2003
ENTRYPOINT ["java", "-jar", "/app/app.jar"]