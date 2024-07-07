# Sử dụng hình ảnh OpenJDK 17 với Alpine Linux
FROM openjdk:17-jdk-alpine
EXPOSE 2003


# Thiết lập biến ARG cho tệp JAR và tệp cấu hình
ARG JAR_FILE=target/*.jar
ARG CONFIG_FILE=src/main/resources/application.yaml

# Thiết lập thư mục làm việc
WORKDIR /app

# Sao chép tệp JAR từ thư mục target vào container
COPY ${JAR_FILE} app.jar

# Sao chép tệp cấu hình application.yml vào container
COPY ${CONFIG_FILE} /app/application.yaml

# Thiết lập lệnh khởi động container
ENTRYPOINT ["java", "-jar", "/app/app.jar", "--spring.config.location=file:/app/application.yaml"]
