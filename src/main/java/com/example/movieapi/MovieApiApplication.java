package com.example.movieapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot Application
 * Movie API - A RESTful API for managing a movie collection
 */
@SpringBootApplication
public class MovieApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieApiApplication.class, args);
        System.out.println("\n===========================================");
        System.out.println("Movie API is running!");
        System.out.println("Access the API at: http://localhost:8080/api/movies");
        System.out.println("===========================================\n");
    }
}
