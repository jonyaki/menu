FROM maven:3.8.5-openjdk-17 as build
COPY . .
RUN mvn clean install -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/myMenu*.jar myMenu.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "myMenu.jar"]
