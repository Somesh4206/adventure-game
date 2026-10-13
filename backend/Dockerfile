# Multi-stage build for Spring Boot application
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/adventure-game-1.0.0.jar app.jar

# Expose port
EXPOSE 10000

# Run the application (PORT will be passed as environment variable by Render)
CMD ["sh", "-c", "java -Dserver.port=${PORT:-10000} -jar app.jar"]
