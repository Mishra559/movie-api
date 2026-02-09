package com.example.movieapi.repository;

import com.example.movieapi.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In-memory repository for managing movies
 * Uses ArrayList for data storage
 */
@Repository
public class MovieRepository {
    private final List<Movie> movies = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public MovieRepository() {
        // Initialize with some sample data
        addMovie(new Movie(null, "The Shawshank Redemption", 
                "Two imprisoned men bond over a number of years", 
                "Drama", 1994, 9.3));
        addMovie(new Movie(null, "The Dark Knight", 
                "When the menace known as the Joker wreaks havoc", 
                "Action", 2008, 9.0));
        addMovie(new Movie(null, "Inception", 
                "A thief who steals corporate secrets through dream-sharing", 
                "Sci-Fi", 2010, 8.8));
    }

    /**
     * Add a new movie to the collection
     * @param movie Movie to add
     * @return The added movie with generated ID
     */
    public Movie addMovie(Movie movie) {
        movie.setId(idCounter.getAndIncrement());
        movies.add(movie);
        return movie;
    }

    /**
     * Find a movie by its ID
     * @param id Movie ID
     * @return Optional containing the movie if found
     */
    public Optional<Movie> findById(Long id) {
        return movies.stream()
                .filter(movie -> movie.getId().equals(id))
                .findFirst();
    }

    /**
     * Get all movies
     * @return List of all movies
     */
    public List<Movie> findAll() {
        return new ArrayList<>(movies);
    }

    /**
     * Update an existing movie
     * @param id Movie ID
     * @param updatedMovie Updated movie data
     * @return Optional containing the updated movie if found
     */
    public Optional<Movie> updateMovie(Long id, Movie updatedMovie) {
        return findById(id).map(movie -> {
            movie.setTitle(updatedMovie.getTitle());
            movie.setDescription(updatedMovie.getDescription());
            movie.setGenre(updatedMovie.getGenre());
            movie.setReleaseYear(updatedMovie.getReleaseYear());
            movie.setRating(updatedMovie.getRating());
            return movie;
        });
    }

    /**
     * Delete a movie by ID
     * @param id Movie ID
     * @return true if deleted, false if not found
     */
    public boolean deleteMovie(Long id) {
        return movies.removeIf(movie -> movie.getId().equals(id));
    }
}
