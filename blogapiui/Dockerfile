# Use official OpenJDK image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy everything into container
COPY . .

# Make mvnw executable
RUN chmod +x mvnw

# Build the app inside the container
RUN ./mvnw clean install

# Expose new port to avoid conflict with original backend
EXPOSE 8081

# Run the app — update jar name if needed
CMD ["java", "-jar", "target/blogapiui-0.0.1-SNAPSHOT.jar"]
