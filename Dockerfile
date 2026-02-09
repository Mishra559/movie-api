# Use Maven with Java 17
FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /app

# Copy everything
COPY . .

# Build the application
RUN mvn clean package -DskipTests

# Expose Spring Boot port
EXPOSE 8080

# Run the jar
CMD ["java", "-jar", "target/movie-api-1.0.0.jar"]
