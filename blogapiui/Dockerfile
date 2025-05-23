# Use official OpenJDK image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy everything into container
COPY . .

# Install Maven inside the container
RUN apt-get update && apt-get install -y maven

# Build the app inside the container
RUN mvn clean install

# Expose port (ensure your app uses this port in application.properties)
EXPOSE 8081

# Run the app — update JAR name if needed
CMD ["java", "-jar", "target/blogapiui-0.0.1-SNAPSHOT.jar"]
