package com.cinema.api.repository;

import com.cinema.api.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    // Metodo mágico: Spring lee esto y crea el SQL automáticamente
    // "¿Existe un boleto con ESTE número de asiento y ESTA función?"
    boolean existsBySeatNumberAndShowtimeId(String seatNumber, Integer showtimeId);

    List<Ticket>findByCustomerId(Integer customerId);


}
