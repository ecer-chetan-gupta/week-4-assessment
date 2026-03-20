package com.capgemini.repository;

import com.capgemini.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByEmail(String email);
    List<Customer> findByIsActiveTrue();
}