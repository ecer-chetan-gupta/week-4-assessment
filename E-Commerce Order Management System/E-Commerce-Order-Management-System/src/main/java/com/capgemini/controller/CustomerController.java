package com.capgemini.controller;

import com.capgemini.entity.Customer;
import com.capgemini.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl service;

    @PostMapping
    public Customer create(@RequestBody Customer c) {
        return service.create(c);
    }

    @GetMapping
    public List<Customer> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @GetMapping("/{id}/restore")
    public void restore(@PathVariable int id) {
        service.restore(id);
    }
}