# syntax=docker/dockerfile:1

FROM maven:3.9.6-eclipse-temurin-8 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -B -q -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -B -DskipTests clean package

FROM tomcat:9.0-jdk8-temurin
ENV TZ=UTC
# optional: clear defaults
RUN rm -rf /usr/local/tomcat/webapps/
COPY --from=build /app/target/ems-crud.war /usr/local/tomcat/webapps/ems-crud.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
