package com.capgemini.service;

import com.capgemini.entity.Customer;
import com.capgemini.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerServiceImpl {

    @Autowired
    private CustomerRepository repo;

    public Customer create(Customer c) {

        if (repo.findByEmail(c.getEmail()).isPresent())
            throw new RuntimeException("Email already exists");

        return repo.save(c);
    }

    public List<Customer> getAll() {
        return repo.findByIsActiveTrue();
    }

    public Customer get(int id) {
        Customer c = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if (!c.isActive())
            throw new RuntimeException("Customer inactive");

        return c;
    }

    public void delete(int id) {
        Customer c = get(id);
        c.setActive(false);
        repo.save(c);
    }

    public void restore(int id) {
        Customer c = repo.findById(id).orElseThrow();
        c.setActive(true);
        repo.save(c);
    }
}