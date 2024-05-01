FROM maven:3.8.3-openjdk-17 AS build

COPY . /usr/src/app
WORKDIR /usr/src/app

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

COPY --from=build /usr/src/app/target/*.jar /usr/app/app.jar

CMD ["java", "-jar", "/usr/app/app.jar"]