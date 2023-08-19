FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/Core-Bank-Authentication-0.0.1-SNAPSHOT.jar Core-Bank-Authentication.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","Core-Bank-Authentication.jar"]