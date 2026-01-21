# --- STAGE 1: BUILD THE APP ---
# We use a Maven image to build the JAR file inside Render
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the application (skipping tests to save time)
RUN mvn clean package -DskipTests

# --- STAGE 2: RUN THE APP ---
# Now we switch to a lighter Java image just to run it
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy the JAR we built in Stage 1 to Stage 2
COPY --from=build /app/target/backend-0.0.1-SNAPSHOT.jar app.jar

# Expose the port
EXPOSE 9090

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]

# we used multi stage build technique..
