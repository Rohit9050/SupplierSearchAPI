# SupplierSearchAPI

## Overview

`SupplierSearchAPI` is a Spring Boot application designed to allow buyers to search for manufacturers based on criteria such as location, business size, and manufacturing processes. The application is built using layered architecture and exposes RESTful APIs for querying supplier data. Swagger is used for documenting and testing the APIs.

## Features

- Search suppliers by location, business size, and manufacturing processes.
- RESTful API endpoints to manage supplier data.
- Swagger integration for API documentation and testing.

## Prerequisites

Before running the application, ensure you have the following installed:

- Java 17 or higher
- Maven 3.6 or higher
- Postman (Optional for manual API testing)
- Web browser (for Swagger UI)

## Running the Application

### Clone the Repository

```bash
git clone https://github.com/Rohit9050/SupplierSearchAPI.git
cd SupplierSearchAPI
```

### Build the Project
Use Maven to build the project and install dependencies:

```bash
mvn clean install
```
### Run the Application
After the build is complete, run the application:

```bash
mvn spring-boot:run
```
The application will start and run on http://localhost:8081 by default.

## Access Swagger API Documentation
Swagger UI is integrated into the application to provide an interactive API documentation and testing interface.

Once the application is running, navigate to the following URL in your web browser to access the Swagger UI:


```bash
http://localhost:8081/swagger-ui/index.html
```
Through the Swagger UI, you can explore and test the available API endpoints, view models, and understand how to interact with the application.

## Sample CURL Commands
Here are a few sample CURL commands for testing the API:

### Get Suppliers by Pagination:
```bash
curl -X 'GET' \
 'http://localhost:8081/api/supplier/query/?pageNumber=0&pageSize=5' \
 -H 'accept: */*'
 ```

### Get Supplier by ID:
``` bash

curl -X 'GET' \
 'http://localhost:8081/api/supplier/query/1' \
 -H 'accept: */*'
 ```
### Search Supplier by Website:
```bash

curl -X 'GET' \
 'http://localhost:8081/api/supplier/query/searchByWebsite?website=www.abccorp.com' \
 -H 'accept: */*'
 ```
### Search Suppliers by Location:
```bash
curl -X 'GET' \
 'http://localhost:8081/api/supplier/query/search?location=Mumbai' \
 -H 'accept: */*'
 ```

### Search Suppliers by Nature of Business
```bash
curl -X 'GET' \
  'http://localhost:8081/api/supplier/query/search?natureOfBusiness=small_scale' \
  -H 'accept: */*'
```
### Add a New Supplier
```bash
curl -X 'POST' \
  'http://localhost:8081/api/supplier/query/' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "supplier_id": 8,
  "company_name": "Makersharks",
  "website": "www.makersharks.com",
  "location": "USA",
  "natureofBusiness": "medium_scale",
  "manufacturingProcesses": [
    "casting"
  ]
}'

```


## Technologies Used
- Spring Boot for building the application
- Maven for dependency management
- Swagger for API documentation
- JPA for database interaction
- MySQL as the database management system

## MySQL Setup Instructions
Ensure that the application.properties file contains the correct MySQL configuration:

#### properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect


## MySQL Requirements for Running the Project
- MySQL should be installed and running.
- Create a database with the name specified in the application.properties file.
- Ensure that the user credentials in application.properties match your MySQL setup.


## Future Enhancements

### Security

- **JWT Authentication**:
  - **Objective**: Implement JWT (JSON Web Token) for secure user authentication and authorization.
  - **Steps**:
    1. **Generate Tokens**: Issue JWTs upon user login.
    2. **Validate Tokens**: Check tokens for validity and expiration on each request.
    3. **Secure Endpoints**: Protect API endpoints by verifying JWTs and user roles.
  - **Implementation**:
    - Use `spring-boot-starter-security` and `jjwt` libraries.
    - Configure Spring Security to use JWT for authentication.
    - Implement a custom JWT filter and entry point.

### Input Validation

- **Enhance Validation**:
  - **Objective**: Improve input validation to ensure data integrity and security.
  - **Steps**:
    1. **Add Validation Rules**: Use annotations and custom validators to enforce data constraints.
    2. **Handle Errors**: Provide clear error messages for invalid inputs.
  - **Implementation**:
    - Apply validation annotations in DTO classes.
    - Create custom validators as needed.



## Contact Information
For any questions or support, feel free to reach out via rohitthakran2525@gmail.com.
