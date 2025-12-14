package com.cinema.api.service;

import com.cinema.api.model.Genre;
import com.cinema.api.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GenreService {

    @Autowired
    GenreRepository genreRepository;

    // 1. Guardar un Género (CON LÓGICA)
    public Genre saveGenre(Genre genre) {
        // REGLA 1: Validar que el nombre no sea nulo ni esté vacío
        if (genre.getName() == null || genre.getName().isEmpty()) {
            // Si no cumple, lanzamos una "excepción" (un error controlado)
            throw new RuntimeException("Error! The gender must have a name.");
        }
        // Si pasó la guardia, entonces sí guardamos
        return genreRepository.save(genre);
    }

    public List<Genre> findAll(){
        return genreRepository.findAll();
    }

    public Genre findById(Integer id){
        return genreRepository.findById(id).orElse(null);
    }

    public void deleteGenre(Integer id){
        genreRepository.deleteById(id);
    }

    public Genre updateGenre(Integer id, Genre newGenre){
        Genre genre = genreRepository.findById(id).orElse(null);

        if (genre != null){
            genre.setName(newGenre.getName());
            return genreRepository.save(genre);

        }
        return null;
    }
}
