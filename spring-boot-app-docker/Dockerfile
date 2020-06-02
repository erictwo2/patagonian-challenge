FROM maven:3-jdk-11 as builder

RUN mkdir -p /build
WORKDIR /build

COPY pom.xml /build

RUN mvn -B dependency:resolve dependency:resolve-plugins

COPY src /build/src

EXPOSE 8080

ENTRYPOINT ["mvn", "spring-boot:run"]