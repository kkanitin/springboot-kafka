FROM gradle:8.5-jdk21 AS build

WORKDIR /app

COPY build.gradle .
COPY settings.gradle .

COPY src src

RUN gradle build --no-daemon

FROM openjdk:21 AS run

COPY --from=build /app/build/libs/producer-1.0.0.jar producer.jar

EXPOSE 8081

CMD ["java", "-jar", "producer.jar"]