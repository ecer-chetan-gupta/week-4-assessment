package com.capgemini.repository;

import com.capgemini.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByCustomerCustomerId(int id);

    List<Order> findByCustomerCustomerIdAndStatus(int id, String status);

    List<Order> findByStatus(String status);
}