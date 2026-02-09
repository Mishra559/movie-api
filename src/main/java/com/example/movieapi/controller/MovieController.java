package com.example.movieapi.controller;

import com.example.movieapi.model.Movie;
import com.example.movieapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for Movie API
 * Provides endpoints for CRUD operations on movies
 */
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    /**
     * Add a new movie
     * POST /api/movies
     * @param movie Movie data in request body
     * @return Created movie with HTTP 201 status
     */
    @PostMapping
    public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
        try {
            Movie createdMovie = movieService.addMovie(movie);
            return new ResponseEntity<>(createdMovie, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get a movie by ID
     * GET /api/movies/{id}
     * @param id Movie ID
     * @return Movie if found with HTTP 200, or HTTP 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable Long id) {
        Optional<Movie> movie = movieService.getMovieById(id);
        if (movie.isPresent()) {
            return new ResponseEntity<>(movie.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                new ErrorResponse("Movie with ID " + id + " not found"), 
                HttpStatus.NOT_FOUND
            );
        }
    }

    /**
     * Get all movies
     * GET /api/movies
     * @return List of all movies with HTTP 200
     */
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    /**
     * Update a movie
     * PUT /api/movies/{id}
     * @param id Movie ID
     * @param movie Updated movie data
     * @return Updated movie with HTTP 200, or HTTP 404 if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        try {
            Optional<Movie> updatedMovie = movieService.updateMovie(id, movie);
            if (updatedMovie.isPresent()) {
                return new ResponseEntity<>(updatedMovie.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(
                    new ErrorResponse("Movie with ID " + id + " not found"), 
                    HttpStatus.NOT_FOUND
                );
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete a movie
     * DELETE /api/movies/{id}
     * @param id Movie ID
     * @return HTTP 204 if deleted, HTTP 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        boolean deleted = movieService.deleteMovie(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(
                new ErrorResponse("Movie with ID " + id + " not found"), 
                HttpStatus.NOT_FOUND
            );
        }
    }

    /**
     * Error response class for consistent error messages
     */
    static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
