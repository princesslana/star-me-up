FROM maven:3-openjdk-11 AS build
WORKDIR /var/src

# Dependencies
COPY pom.xml .
RUN mvn -B dependency:resolve

# Build
COPY . .
RUN mvn -B package

FROM openjdk:11-jre
WORKDIR /var/app

COPY --from=build /var/src/target/starmeup.jar .

CMD java -jar starmeup.jar
