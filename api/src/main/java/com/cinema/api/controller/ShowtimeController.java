package com.cinema.api.controller;

import com.cinema.api.model.Showtime;
import com.cinema.api.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/showtimes")
public class ShowtimeController {

    @Autowired
    private ShowtimeService showtimeService;

    @PostMapping
    public ResponseEntity<Showtime> createShowtime(@RequestBody Showtime showtime){
        Showtime newShowtime = showtimeService.saveShowtime(showtime);
        return ResponseEntity.ok(newShowtime);
    }
    @GetMapping
    public List<Showtime> getAllShowTime(){
        return showtimeService.findAllShowtime();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Showtime> getShowtimeById(@PathVariable Integer id){
        Showtime showtime = showtimeService.findById(id);
        if (showtime == null){
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(showtime);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShowtime(@PathVariable Integer id){
        showtimeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Showtime> updateShowTime(@PathVariable Integer id, @RequestBody Showtime showtime){
        Showtime updateShowtime = showtimeService.updateShowtime(id, showtime);
        if (updateShowtime == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updateShowtime);
    }
}

