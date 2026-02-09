package com.example.movieapi.service;

import com.example.movieapi.model.Movie;
import com.example.movieapi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for movie operations with business logic and validation
 */
@Service
public class MovieService {
    
    @Autowired
    private MovieRepository movieRepository;

    /**
     * Add a new movie with validation
     * @param movie Movie to add
     * @return The added movie
     * @throws IllegalArgumentException if validation fails
     */
    public Movie addMovie(Movie movie) {
        validateMovie(movie);
        return movieRepository.addMovie(movie);
    }

    /**
     * Get a movie by ID
     * @param id Movie ID
     * @return Optional containing the movie if found
     */
    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    /**
     * Get all movies
     * @return List of all movies
     */
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    /**
     * Update an existing movie
     * @param id Movie ID
     * @param movie Updated movie data
     * @return Optional containing the updated movie if found
     * @throws IllegalArgumentException if validation fails
     */
    public Optional<Movie> updateMovie(Long id, Movie movie) {
        validateMovie(movie);
        return movieRepository.updateMovie(id, movie);
    }

    /**
     * Delete a movie by ID
     * @param id Movie ID
     * @return true if deleted, false if not found
     */
    public boolean deleteMovie(Long id) {
        return movieRepository.deleteMovie(id);
    }

    /**
     * Validate movie data
     * @param movie Movie to validate
     * @throws IllegalArgumentException if validation fails
     */
    private void validateMovie(Movie movie) {
        if (movie.getTitle() == null || movie.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title is required and cannot be empty");
        }
        
        if (movie.getDescription() == null || movie.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Description is required and cannot be empty");
        }
        
        if (movie.getGenre() == null || movie.getGenre().trim().isEmpty()) {
            throw new IllegalArgumentException("Genre is required and cannot be empty");
        }
        
        if (movie.getReleaseYear() < 1800 || movie.getReleaseYear() > 2030) {
            throw new IllegalArgumentException("Release year must be between 1800 and 2030");
        }
        
        if (movie.getRating() < 0.0 || movie.getRating() > 10.0) {
            throw new IllegalArgumentException("Rating must be between 0.0 and 10.0");
        }
    }
}
