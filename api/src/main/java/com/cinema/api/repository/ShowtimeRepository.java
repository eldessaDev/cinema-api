package com.cinema.api.repository;

import com.cinema.api.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowtimeRepository extends JpaRepository<Showtime, Integer> {
}
