# Movie API - RESTful API for Managing Movies

A simple Java-based RESTful API built with Spring Boot for managing a collection of movies (Netflix-style). This application demonstrates CRUD operations, input validation, and in-memory data storage.

## Features

- **In-memory data storage** using ArrayList
- **RESTful API** endpoints for CRUD operations
- **Input validation** for all required fields
- **Error handling** with appropriate HTTP status codes
- **Pre-loaded sample data** for testing

## Technologies Used

- Java 17
- Spring Boot 3.2.0
- Maven
- In-memory ArrayList storage

## Project Structure

```
movie-api/
├── src/
│   └── main/
│       ├── java/com/example/movieapi/
│       │   ├── MovieApiApplication.java      # Main application class
│       │   ├── controller/
│       │   │   └── MovieController.java      # REST endpoints
│       │   ├── model/
│       │   │   └── Movie.java                # Movie data model
│       │   ├── repository/
│       │   │   └── MovieRepository.java      # In-memory data storage
│       │   └── service/
│       │       └── MovieService.java         # Business logic & validation
│       └── resources/
│           └── application.properties         # Application configuration
└── pom.xml                                    # Maven dependencies
```

## Movie Model

The `Movie` class has the following properties:

- `id` (Long): Unique identifier (auto-generated)
- `title` (String): Movie title (required)
- `description` (String): Movie description (required)
- `genre` (String): Movie genre (required)
- `releaseYear` (int): Year of release (1800-2030)
- `rating` (double): Movie rating (0.0-10.0)

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Installation & Running

1. **Navigate to the project directory:**
   ```bash
   cd movie-api
   ```

2. **Build the project:**
   ```bash
   mvn clean install
   ```

3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

   Or run the JAR file:
   ```bash
   java -jar target/movie-api-1.0.0.jar
   ```

4. **The API will be available at:**
   ```
   http://localhost:8080/api/movies
   ```

## API Endpoints

### 1. Add a New Movie

**Endpoint:** `POST /api/movies`

**Request Body:**
```json
{
  "title": "The Matrix",
  "description": "A computer hacker learns about the true nature of reality",
  "genre": "Sci-Fi",
  "releaseYear": 1999,
  "rating": 8.7
}
```

**Response:** `201 Created`
```json
{
  "id": 4,
  "title": "The Matrix",
  "description": "A computer hacker learns about the true nature of reality",
  "genre": "Sci-Fi",
  "releaseYear": 1999,
  "rating": 8.7
}
```

**cURL Example:**
```bash
curl -X POST http://localhost:8080/api/movies \
  -H "Content-Type: application/json" \
  -d '{
    "title": "The Matrix",
    "description": "A computer hacker learns about the true nature of reality",
    "genre": "Sci-Fi",
    "releaseYear": 1999,
    "rating": 8.7
  }'
```

### 2. Get a Single Movie by ID

**Endpoint:** `GET /api/movies/{id}`

**Response:** `200 OK`
```json
{
  "id": 1,
  "title": "The Shawshank Redemption",
  "description": "Two imprisoned men bond over a number of years",
  "genre": "Drama",
  "releaseYear": 1994,
  "rating": 9.3
}
```

**cURL Example:**
```bash
curl http://localhost:8080/api/movies/1
```

**Error Response (404):**
```json
{
  "message": "Movie with ID 999 not found"
}
```

### 3. Get All Movies

**Endpoint:** `GET /api/movies`

**Response:** `200 OK`
```json
[
  {
    "id": 1,
    "title": "The Shawshank Redemption",
    "description": "Two imprisoned men bond over a number of years",
    "genre": "Drama",
    "releaseYear": 1994,
    "rating": 9.3
  },
  {
    "id": 2,
    "title": "The Dark Knight",
    "description": "When the menace known as the Joker wreaks havoc",
    "genre": "Action",
    "releaseYear": 2008,
    "rating": 9.0
  }
]
```

**cURL Example:**
```bash
curl http://localhost:8080/api/movies
```

### 4. Update a Movie

**Endpoint:** `PUT /api/movies/{id}`

**Request Body:**
```json
{
  "title": "Inception Updated",
  "description": "A thief who steals corporate secrets through dream-sharing technology",
  "genre": "Sci-Fi",
  "releaseYear": 2010,
  "rating": 9.0
}
```

**Response:** `200 OK`

**cURL Example:**
```bash
curl -X PUT http://localhost:8080/api/movies/3 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Inception Updated",
    "description": "A thief who steals corporate secrets through dream-sharing technology",
    "genre": "Sci-Fi",
    "releaseYear": 2010,
    "rating": 9.0
  }'
```

### 5. Delete a Movie

**Endpoint:** `DELETE /api/movies/{id}`

**Response:** `204 No Content`

**cURL Example:**
```bash
curl -X DELETE http://localhost:8080/api/movies/3
```

## Input Validation

The API validates the following constraints:

- **Title**: Required, cannot be empty
- **Description**: Required, cannot be empty
- **Genre**: Required, cannot be empty
- **Release Year**: Must be between 1800 and 2030
- **Rating**: Must be between 0.0 and 10.0

### Validation Error Example

**Request:**
```json
{
  "title": "",
  "description": "Test",
  "genre": "Action",
  "releaseYear": 2020,
  "rating": 8.0
}
```

**Response:** `400 Bad Request`
```json
{
  "message": "Title is required and cannot be empty"
}
```

## Sample Data

The application comes pre-loaded with three movies:

1. The Shawshank Redemption (1994) - Drama - 9.3
2. The Dark Knight (2008) - Action - 9.0
3. Inception (2010) - Sci-Fi - 8.8

## Testing the API

### Using cURL

Test all endpoints using the cURL examples provided above.

### Using Postman

1. Import the endpoints into Postman
2. Set the base URL to `http://localhost:8080`
3. Use the request examples provided in this README

### Using Browser

For GET requests, you can simply use your browser:
- All movies: `http://localhost:8080/api/movies`
- Single movie: `http://localhost:8080/api/movies/1`

## Implementation Details

### Data Storage
- Uses an `ArrayList<Movie>` for in-memory storage
- Data is lost when the application stops
- `AtomicLong` is used for thread-safe ID generation

### Architecture
- **Controller Layer**: Handles HTTP requests and responses
- **Service Layer**: Contains business logic and validation
- **Repository Layer**: Manages data storage operations
- **Model Layer**: Defines the data structure

### Error Handling
- Returns appropriate HTTP status codes (200, 201, 204, 400, 404)
- Provides meaningful error messages in JSON format

## Future Enhancements

- Add database persistence (H2, MySQL, PostgreSQL)
- Implement pagination and sorting
- Add search and filter capabilities
- Implement authentication and authorization
- Add unit and integration tests
- Create Swagger/OpenAPI documentation

## License

This project is for educational purposes.

## Author

Created as a demonstration of RESTful API development with Spring Boot.
