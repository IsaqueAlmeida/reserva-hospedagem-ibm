
# Use the official Maven image as the base image
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and download the dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code
COPY src/ ./src/

# Build the application
RUN mvn package -DskipTests

# Use the official OpenJDK image as the base image for running the application
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage to the container
COPY --from=build /app/target/reserva-hospedagem-ibm.jar ./app.jar

# Copy the application.properties file from the host to the container
COPY application.properties .

# Expose the port your application is listening on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
