FROM maven:3.8.3-openjdk-11

# Set the working directory to /app
WORKDIR /app

# Copy the Maven wrapper to the container
COPY mvnw .
COPY .mvn .mvn

# Copy the Maven project file to the container
COPY pom.xml .

# Run Maven to download dependencies
RUN mvn dependency:go-offline

# Copy the source code to the container
COPY src/ ./src/

# Build the application
RUN mvn package

# Expose port 8080 for the Kafka producer service
EXPOSE 8080

# Set the entrypoint to run the Kafka producer service
ENTRYPOINT ["java", "-jar", "target/kafka-producer-service-1.0-SNAPSHOT.jar"]