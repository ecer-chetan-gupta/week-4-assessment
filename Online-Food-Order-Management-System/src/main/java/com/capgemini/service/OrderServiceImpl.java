package com.capgemini.service;


import com.capgemini.entity.Order;
import com.capgemini.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repo;

    @Override
    public Map<String, Object> createOrder(Order order) {

        if (order.getQuantity() <= 0) {
            return Map.of("message", "Quantity must be greater than 0");
        }

        order.setStatus("PLACED");

        Order saved = repo.save(order);

        return Map.of(
                "message", "Order created successfully",
                "orderId", saved.getOrderId()
        );
    }

    @Override
    public Order getOrderById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public List<Order> getAllOrders() {
        return repo.findAll();
    }

    @Override
    public Map<String, String> updateOrder(int id, Order newOrder) {

        Order existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (existing.getStatus().equals("CANCELLED")) {
            return Map.of("message", "Cannot update cancelled order");
        }

        if (newOrder.getQuantity() <= 0) {
            return Map.of("message", "Quantity must be greater than 0");
        }

        existing.setCustomerName(newOrder.getCustomerName());
        existing.setFoodItem(newOrder.getFoodItem());
        existing.setQuantity(newOrder.getQuantity());

        repo.save(existing);

        return Map.of("message", "Order updated successfully");
    }

    @Override
    public Map<String, String> cancelOrder(int id) {

        Order order = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus("CANCELLED");

        repo.save(order);

        return Map.of("message", "Order cancelled successfully");
    }
}