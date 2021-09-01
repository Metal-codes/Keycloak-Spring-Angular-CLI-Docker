#Backend
##Build stage
FROM maven:3.6.0-jdk-11 as mvn-builder
COPY pom.xml /app/pom.xml
COPY src /app/src
WORKDIR /app
RUN mvn clean package

##Run stage
FROM openjdk:11
COPY --from=mvn-builder /app/target/staz-aleksander-gut-*-SNAPSHOT.jar /app/
WORKDIR /app
EXPOSE 8888
CMD java -jar staz-aleksander-gut-*-SNAPSHOT.jar
