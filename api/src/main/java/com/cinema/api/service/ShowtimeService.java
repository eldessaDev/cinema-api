package com.cinema.api.service;

import com.cinema.api.model.Genre;
import com.cinema.api.repository.ShowtimeRepository;
import com.cinema.api.model.Showtime;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShowtimeService {

    @Autowired
    ShowtimeRepository showtimeRepository;

    public Showtime saveShowtime(Showtime showtime){
        if (showtime.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Error, showtime must have a price ");
        }
        return showtimeRepository.save(showtime);
    }

    public List<Showtime> findAllShowtime(){
        return showtimeRepository.findAll();
    }

    public Showtime findById(Integer id){
        return showtimeRepository.findById(id).orElse(null);
    }

    public void deleteById(Integer id){
        showtimeRepository.deleteById(id);
    }

    public Showtime updateShowtime(Integer id, Showtime newShowtime){
        Showtime showtime = showtimeRepository.findById(id).orElse(null);
        if (showtime != null){
            showtime.setPrice(newShowtime.getPrice());
            showtime.setStartTime(newShowtime.getStartTime());
            return showtimeRepository.save(showtime);

        }
        return null;
    }


}




