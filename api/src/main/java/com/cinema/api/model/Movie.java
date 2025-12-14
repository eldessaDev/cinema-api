package com.cinema.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "movies") // <--- ESTO mira a SQL (Plural)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    private String director;

    // --- CORRECCIÓN AQUÍ ---
    // Antes tenías "genres" (plural). Lo cambiamos a "genre" (singular).
    // ¿Por qué? Porque una película tiene UN solo género.
    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false) // Esto mira a la columna SQL
    private Genre genre; // <--- ESTA ES LA VARIABLE QUE MIRA EL MAPPEDBY DE ARRIBA

    @JsonIgnore
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Showtime> showtimes;

    public Movie(){}

    public Movie(String title, String director){
        this.title = title;
        this.director = director;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    // OJO: Actualicé los Getters y Setters a Singular también
    public Genre getGenre() { return genre; }
    public void setGenre(Genre genre) { this.genre = genre; }

    public List<Showtime> getShowtimes() { return showtimes; }
    public void setShowtimes(List<Showtime> showtimes) { this.showtimes = showtimes; }
}