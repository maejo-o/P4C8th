FROM maven:3.8.4-openjdk-8 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

FROM openjdk:8-jre-slim
COPY --from=build /app/target/log4shell-demo-1.0-SNAPSHOT.jar /log4shell-demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/log4shell-demo.jar"]
