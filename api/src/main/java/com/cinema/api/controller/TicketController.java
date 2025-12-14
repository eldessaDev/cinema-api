package com.cinema.api.controller;

import com.cinema.api.model.Ticket;
import com.cinema.api.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<?> createTicket(@RequestBody Ticket ticket) {

        try{
            Ticket newTicket = ticketService.createTicket(ticket);
            return ResponseEntity.ok(newTicket);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Ticket> getAllTickets(){
        return ticketService.findAllTickets();
    }

    @GetMapping("/customer/{customerId}")
    public List<Ticket>getTicketsByCustomerId(@PathVariable Integer customerId){
        return ticketService.getTicketByCustomerId(customerId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketbyId(@PathVariable Integer id){
        Ticket ticket = ticketService.findById(id);
        if(ticket == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/customer/{customerId}/total")
    public ResponseEntity<Double> getTotalSpent(@PathVariable Integer customerId){
        Double total = ticketService.calculateTotalSpent(customerId);
        return ResponseEntity.ok(total);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Integer id){
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }

   @PutMapping("/{id}")
    public ResponseEntity<?> updateTicket(@PathVariable Integer id, @RequestBody Ticket ticket){
        try{
             Ticket updateTicket = ticketService.updateTicket(id, ticket);
             if (updateTicket == null){
                 return ResponseEntity.notFound().build();
             }
             return ResponseEntity.ok(updateTicket);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

   }


}
