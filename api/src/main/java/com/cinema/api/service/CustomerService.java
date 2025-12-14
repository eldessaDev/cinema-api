package com.cinema.api.service;

import com.cinema.api.model.Customer;
import com.cinema.api.repository.CustomerRepository;
import com.cinema.api.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    @Autowired
    TicketRepository ticketRepository;

    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Integer id){
        return customerRepository.findById(id).orElse(null);

    }

    public void deleteCustomer(Integer id){
        customerRepository.deleteById(id);
    }

    public Customer updateCustomer(Integer id, Customer newCustomer){
        Customer customer =customerRepository.findById(id).orElse(null);
        if (customer != null){
            customer.setName(newCustomer.getName());
            customer.setEmail(newCustomer.getEmail());
            return customerRepository.save(customer);
        }
        return null;

    }



}
