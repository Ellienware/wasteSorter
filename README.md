# wasteSorter

wasteSorter is a Spring Boot application designed to help categorize waste materials and provide users with disposal guidelines and recycling tips. The application uses an in-memory H2 database for storing and retrieving waste category data, and it exposes RESTful endpoints for performing CRUD (Create, Read, Update, Delete) operations on waste categories.

## Features

- **CRUD Operations**: Provides RESTful APIs to create, read, update, and delete waste categories.
- **Waste Categories**: Categorize different types of waste (e.g., Plastic, Glass, Paper) with disposal guidelines and recycling tips.
- **In-Memory H2 Database**: Uses an in-memory H2 database for testing and development purposes.
- **Validation**: Includes validation for waste category fields like `name`, `disposalGuidelines`, and `recyclingTips`.

## Requirements

- JDK 17 or higher
- Maven 3.x or higher
- Spring Boot

## Getting Started

### Clone the repository

```bash
git clone https://github.com/Ellienware/wasteSorter.git
cd wasteSorter

Build and Run the Application

To build and run the application, execute the following command in your terminal:

mvn clean install

mvn spring-boot:run

This will start the Spring Boot application on port 8080.
Access the H2 Database Console

The H2 database console can be accessed by navigating to http://localhost:8081/h2-console. You can use the following credentials to log in:

    JDBC URL: jdbc:h2:mem:wasteDB
    Username: ellienware
    Password: password

To run tests, execute the following command in your terminal:

mvn clean test

mvn test

