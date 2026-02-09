# Quick Start Guide - Movie API

## Running the Application

### Option 1: Using Maven (Recommended)

```bash
# Navigate to project directory
cd movie-api

# Run the application
mvn spring-boot:run
```

### Option 2: Build and Run JAR

```bash
# Build the project
mvn clean package

# Run the JAR
java -jar target/movie-api-1.0.0.jar
```

## Quick Test Commands

Once the application is running at `http://localhost:8080`, try these commands:

### 1. Get all movies (includes 3 pre-loaded samples)
```bash
curl http://localhost:8080/api/movies
```

### 2. Get a specific movie (ID: 1)
```bash
curl http://localhost:8080/api/movies/1
```

### 3. Add a new movie
```bash
curl -X POST http://localhost:8080/api/movies \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Pulp Fiction",
    "description": "The lives of two mob hitmen, a boxer, and a pair of diner bandits intertwine",
    "genre": "Crime",
    "releaseYear": 1994,
    "rating": 8.9
  }'
```

### 4. Update a movie (ID: 1)
```bash
curl -X PUT http://localhost:8080/api/movies/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "The Shawshank Redemption - Updated",
    "description": "Two imprisoned men bond over a number of years",
    "genre": "Drama",
    "releaseYear": 1994,
    "rating": 9.5
  }'
```

### 5. Delete a movie (ID: 3)
```bash
curl -X DELETE http://localhost:8080/api/movies/3
```

## Testing Validation

Try adding a movie with invalid data:

```bash
curl -X POST http://localhost:8080/api/movies \
  -H "Content-Type: application/json" \
  -d '{
    "title": "",
    "description": "Test",
    "genre": "Action",
    "releaseYear": 3000,
    "rating": 15.0
  }'
```

You should receive validation errors!

## Using a GUI Tool (Postman/Insomnia)

1. Set base URL: `http://localhost:8080`
2. Import the endpoints:
   - GET    /api/movies
   - GET    /api/movies/{id}
   - POST   /api/movies
   - PUT    /api/movies/{id}
   - DELETE /api/movies/{id}
3. Use the JSON examples from the main README

## Troubleshooting

**Port 8080 already in use?**
- Change the port in `src/main/resources/application.properties`
- Add: `server.port=8081`

**Maven not found?**
- Install Maven: https://maven.apache.org/download.cgi
- Or use the Maven wrapper: `./mvnw spring-boot:run`

**Java version error?**
- Ensure Java 17 or higher is installed
- Check version: `java -version`
