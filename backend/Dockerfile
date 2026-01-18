# Use Eclipse Temurin (The modern standard for Java 21)
FROM eclipse-temurin:21-jdk

# Set working folder inside the container
WORKDIR /app

# Copy the JAR file we built
COPY target/backend-0.0.1-SNAPSHOT.jar app.jar

# Expose the port
EXPOSE 9090

# Command to run the app
ENTRYPOINT ["java", "-jar", "app.jar"]