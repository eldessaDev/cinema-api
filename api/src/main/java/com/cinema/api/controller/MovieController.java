package com.cinema.api.controller;

import com.cinema.api.model.Movie;
import com.cinema.api.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie){
        Movie newMovie = movieService.saveMovie(movie);
        return ResponseEntity.ok(newMovie);
    }
    @GetMapping
    public List<Movie> getAllMovie(){

        return movieService.findAllMovies();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Integer id){
        Movie movie = movieService.findById(id);
        if (movie == null){
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Integer id){
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie>updateMovie(@PathVariable Integer id, @RequestBody Movie newMovie){
        Movie updateMovie = movieService.updateMovie(id, newMovie);
        if (updateMovie == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updateMovie);

    }
}
