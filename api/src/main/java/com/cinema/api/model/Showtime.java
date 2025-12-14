package com.cinema.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;    // Para precios (DECIMAL en SQL)
import java.time.LocalDateTime; // Para fechas (DATETIME en SQL)

@Entity
@Table(name = "showtimes") // Nombre de la tabla en SQL
public class Showtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // IMPORTANTE: En SQL se llama "start_time", en Java "startTime".
    // Usamos @Column para hacer el puente entre los dos nombres.
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private BigDecimal price;

    // --- LA CONEXIÓN CON EL PADRE (MOVIE) ---
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false) // La columna FK en SQL
    private Movie movie; // <--- SINGULAR (Esto es lo que busca el mappedBy de Movie)

    // Constructor vacío
    public Showtime() {}

    // Constructor con datos
    public Showtime(LocalDateTime startTime, BigDecimal price, Movie movie) {
        this.startTime = startTime;
        this.price = price;
        this.movie = movie;
    }

    // --- GETTERS Y SETTERS ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Movie getMovie() { return movie; }
    public void setMovie(Movie movie) { this.movie = movie; }
}