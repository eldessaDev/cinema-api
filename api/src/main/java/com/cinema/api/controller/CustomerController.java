package com.cinema.api.controller;

import com.cinema.api.model.Customer;
import com.cinema.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer){
        try {
            Customer newCustomer = customerService.saveCustomer(customer);
            return ResponseEntity.ok(newCustomer);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: Duplicate email or invalid data");
        }
    }

    @GetMapping
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer>getCustomerById(@PathVariable Integer id){
        Customer customer = customerService.getCustomerById(id);
        if (customer == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{id}")
// Recuerda el comodín <?> para poder devolver Error o Cliente
    public ResponseEntity<?> updateCustomer(@PathVariable Integer id, @RequestBody Customer updateCustomer){
        try {
            Customer customer = customerService.updateCustomer(id, updateCustomer);

            if (customer == null){
                return ResponseEntity.notFound().build(); // Cambiamos a 404
            }
            return ResponseEntity.ok(customer);

        } catch (RuntimeException e) {
            // Aquí atrapamos el error si el email ya existe
            return ResponseEntity.badRequest().body("Error: Email duplicado o datos inválidos");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id){
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }







}
