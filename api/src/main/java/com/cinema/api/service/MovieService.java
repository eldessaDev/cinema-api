package com.cinema.api.service;

import com.cinema.api.model.Genre;
import com.cinema.api.repository.MovieRepository;
import com.cinema.api.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public Movie saveMovie(Movie movie){
       if (movie.getTitle() == null || movie.getTitle().isEmpty()){
           throw new RuntimeException("Error! The movie must have a title.");
       }
       movie.setTitle(movie.getTitle());
       return movieRepository.save(movie);
    }

    public List<Movie> findAllMovies(){
        return movieRepository.findAll();
    }

    public Movie findById(Integer id){
        return movieRepository.findById(id).orElse(null);
    }

    public void deleteMovie(Integer id){
        movieRepository.deleteById(id);
    }

    public Movie updateMovie(Integer id, Movie newMovie){
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie != null){
            movie.setTitle(newMovie.getTitle());
            movie.setDirector(newMovie.getDirector());
            movie.setGenre(newMovie.getGenre());
            return movieRepository.save(movie);
        }
        return null;
    }



}
