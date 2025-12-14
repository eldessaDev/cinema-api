package com.cinema.api.controller;

import com.cinema.api.model.Genre;
import com.cinema.api.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre){
        Genre newGenre = genreService.saveGenre(genre);
        return ResponseEntity.ok(newGenre);
    }

    @GetMapping
    public List<Genre> getAllGenre(){
        return genreService.findAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre>getGenreById(@PathVariable Integer id){
        Genre genre = genreService.findById(id);
        if (genre == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(genre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Integer id){
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Integer id, @RequestBody Genre genre){
        Genre updateGenre = genreService.updateGenre(id, genre);

        if(updateGenre == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updateGenre);
    }

}
