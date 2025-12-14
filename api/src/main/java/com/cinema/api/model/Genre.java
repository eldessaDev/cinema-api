package com.cinema.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "genres") // <--- ESTO mira a SQL (Plural)
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    // --- CORRECCIÓN AQUÍ ---
    // mappedBy = "genre" (Singular)
    // ¿Por qué? Porque en el archivo de abajo (Movie), la variable se llama "genre".
    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Movie> movies;

    public Genre(){}

    public Genre(String name){
        this.name = name;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Movie> getMovies() { return movies; }
    public void setMovies(List<Movie> movies) { this.movies = movies; }
}