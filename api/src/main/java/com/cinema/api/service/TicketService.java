package com.cinema.api.service;

import com.cinema.api.model.Customer;
import com.cinema.api.model.Showtime;
import com.cinema.api.model.Ticket;
import com.cinema.api.repository.CustomerRepository;
import com.cinema.api.repository.ShowtimeRepository;
import com.cinema.api.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowtimeRepository showtimeRepository;

    @Autowired
    CustomerRepository customerRepository;


    public Ticket createTicket(Ticket ticket){
// PASO 1: Obtener el ID de la función que viene en el boleto
        // El cliente dice: "Quiero ir a la función con ID X"
        Integer showtimeId = ticket.getShowtime().getId();

        // PASO 2: Buscar esa función en la Base de Datos (Aquí fue donde te trabaste)
        // No puedes usar 'showtime' si no lo has buscado primero.
        Showtime showtimeFromDb = showtimeRepository.findById(showtimeId).orElse(null);

        // Validación A: ¿Existe la función?
        if (showtimeFromDb == null) {
            throw new RuntimeException("Error: The function does not exist.");
        }

        // PASO 3: Verificar si el asiento está ocupado (Usando el metodo mágico)
        String seatRequest = ticket.getSeatNumber();
        boolean isSeatTaken = ticketRepository.existsBySeatNumberAndShowtimeId(seatRequest, showtimeId);

        // Validación B: ¿El asiento está ocupado?
        if (isSeatTaken) {
            throw new RuntimeException("Error: El asiento " + seatRequest + " ya está ocupado.");
        }

        // PASO 4: Si pasamos las validaciones, asignamos la función real y guardamos
        ticket.setShowtime(showtimeFromDb); // Vinculamos la función encontrada

        // --- NUEVA LÓGICA: Validar y Asignar Cliente ---
        if (ticket.getCustomer() != null && ticket.getCustomer().getId() != null) {
            // Buscamos al cliente en la BD usando el ID que viene en el JSON
            Customer customer = customerRepository.findById(ticket.getCustomer().getId())
                    .orElse(null);

            if (customer == null) {
                throw new RuntimeException("Error: El cliente no existe.");
            }
            // Si existe, se lo asignamos al ticket para que se guarde bien la relación
            ticket.setCustomer(customer);
        } else {
            throw new RuntimeException("Error: We need a client to buy a Ticket");
            // Opcional: Si quieres obligar a que siempre haya cliente, descomenta esto:
            // throw new RuntimeException("Error: Se requiere un cliente para comprar.");
        }
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getTicketByCustomerId(Integer customerId){
        return ticketRepository.findByCustomerId(customerId);
    }

    public Double calculateTotalSpent(Integer customerId){
        List<Ticket>myTickets = getTicketByCustomerId(customerId);

        Double totalSpent = 0.0;

        for (Ticket ticket:myTickets){
            totalSpent += ticket.getShowtime().getPrice().doubleValue();

        }
        return totalSpent;

    }

    public List<Ticket> findAllTickets(){
        return ticketRepository.findAll();
    }

    public Ticket findById(Integer id){
        return ticketRepository.findById(id).orElse(null);
    }

    public void deleteTicket(Integer id){
        ticketRepository.deleteById(id);
    }

    public Ticket updateTicket(Integer id, Ticket newTicket){
        // 1. Buscamos el ticket viejo (el que ya existe)
        Ticket oldTicket = ticketRepository.findById(id).orElse(null);

        if (oldTicket == null){
            return null;// Si no existe el ticket, no hay nada que actualizar
        }
        // 2. Extraemos los datos NUEVOS que el cliente quiere
        // Si vienen nulos, mantenemos los viejos (por seguridad)
        String newSeat = newTicket.getSeatNumber();
        Integer newShowtimeId = newTicket.getShowtime().getId();

        // 3. LA GRAN VALIDACIÓN:
        // Solo verificamos si el asiento O la función son diferentes a lo que ya tenía.
        // Si quiere el mismo asiento en la misma función, no pasa nada.
        boolean changeSeat = !newSeat.equals(oldTicket.getSeatNumber());
        boolean changeShowTime = !newShowtimeId.equals(oldTicket.getShowtime().getId());
        if (changeSeat||changeShowTime){
            boolean isTaken = ticketRepository.existsBySeatNumberAndShowtimeId(newSeat, newShowtimeId);
            if (isTaken){
                throw new RuntimeException("Error: The new seat " + newSeat + " is not available.");
            }
        }
        //Si pasamos la validación, actualizamos los datos
        oldTicket.setSeatNumber(newSeat);

        // Necesitamos buscar el objeto Showtime completo para asignarlo (igual que en el Create)
        Showtime newShowtime = showtimeRepository.findById(newShowtimeId).orElse(null);
        if (newShowtime == null){
            throw new RuntimeException("Error: The new function does not exist.");
        }
        oldTicket.setShowtime(newShowtime);

        return ticketRepository.save(oldTicket);
    }









}
