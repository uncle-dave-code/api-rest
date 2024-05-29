FROM maven:3.9.6-eclipse-temurin-21 as builder
WORKDIR /build
COPY . .
RUN mvn clean install -DskipTests

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=builder /build/target/*.jar app.jar

ENV SPRING_PROFILES_ACTIVE=prod

EXPOSE 8081
CMD ["java", "-jar", "app.jar"]