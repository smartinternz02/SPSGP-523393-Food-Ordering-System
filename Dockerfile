# Use the official Maven image as the build environment
FROM maven:3.8.4-openjdk-11 AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file to the container
COPY pom.xml .

# Download the project dependencies
RUN mvn dependency:go-offline

# Copy the project source code to the container
COPY src/ ./src/

# Build the application
RUN mvn package -DskipTests

# Use a slim Java 11 runtime image as the base image
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the builder stage to the container
COPY --from=builder /app/target/bookStore-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application is running on
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "app.jar"]
