# Book Information API

## Overview

This Spring Boot application integrates with the Open Library Books API to fetch and display book details. It exposes a RESTful endpoint that accepts JSON requests and returns JSON responses.

## Features

- Fetch book details by ISBN using the Open Library Books API.
- Error handling for scenarios such as invalid ISBNs, missing book information, and connectivity problems.
- RESTful endpoint that accepts and returns JSON.
- Swagger UI for API documentation and testing.

## Prerequisites

- Java 17 or later
- Maven

## Installation

1. Clone the repository:
    ```bash
    git clone <repository-url>
    cd <repository-directory>
    ```

2. Build the project using Maven:
    ```bash
    mvn clean install
    ```

3. Run the application:
    ```bash
    mvn spring-boot:run
    ```

## Endpoints

### Fetch Book Details

- **URL:** `/book`
- **Method:** `POST`
- **Request:**
    ```json
    {
        "bookIsbn": "1234567"
    }
    ```
- **Response:**
    ```json
    {
        "bookIsbn": "1234567",
        "title": "Lord of the Rings",
        "publishers": ["Publisher Name"],
        "authors": [
            {"name": "Author1"},
            {"name": "Author2"}
        ],
        "totalPages": 500,
        "publishedDate": "23-05-2023"
    }
    ```

### Error Response

- **Response:**
    ```json
    {
        "message": "An unexpected error occurred",
        "details": "I/O error on GET request for 'https://openlibrary.org/api/books': Connection timed out: no further information"
    }
    ```

## Testing with Postman

1. Open Postman.
2. Create a new POST request.
3. Set the request URL to:
    ```
    http://localhost:8081/book
    ```
4. Set the request body to raw JSON:
    ```json
    {
        "bookIsbn": "1234567"
    }
    ```
5. Send the request and check the response.

## Swagger UI

Swagger UI is available to explore and test the API endpoints.

1. Open a web browser.
2. Navigate to the Swagger UI:
    ```
    http://localhost:8081/swagger-ui.html
    ```
3. Use the Swagger UI to test the API endpoints.

## Configuration

You can externalize any necessary configuration properties using `src/main/resources/application.properties` or `src/main/resources/application.yml`.

## Dependencies

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.3.0</version>
    </dependency>
</dependencies>
