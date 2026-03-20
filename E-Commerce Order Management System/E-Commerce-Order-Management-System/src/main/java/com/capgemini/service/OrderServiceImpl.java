package com.capgemini.service;

import com.capgemini.dto.OrderDTO;
import com.capgemini.entity.*;
import com.capgemini.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class OrderServiceImpl {

    @Autowired
    private OrderRepository repo;

    @Autowired
    private CustomerRepository customerRepo;

    private boolean validStatus(String s) {
        return List.of("PLACED","CONFIRMED","SHIPPED","DELIVERED","CANCELLED")
                .contains(s);
    }

    public Order place(int customerId, OrderDTO dto) {

        Customer c = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if (!validStatus(dto.getStatus()))
            throw new RuntimeException("Invalid status");

        Order o = new Order();

        o.setProductName(dto.getProductName());
        o.setQuantity(dto.getQuantity());
        o.setPricePerUnit(dto.getPricePerUnit());

        // 🔥 DERIVED FIELD
        o.setTotalAmount(dto.getPricePerUnit()
                .multiply(BigDecimal.valueOf(dto.getQuantity())));

        o.setStatus(dto.getStatus());
        o.setCustomer(c);

        return repo.save(o);
    }

    public Order cancel(int id) {

        Order o = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (o.getStatus().equals("SHIPPED") || o.getStatus().equals("DELIVERED"))
            throw new RuntimeException("Cannot cancel order");

        o.setStatus("CANCELLED");

        return repo.save(o);
    }

    public List<Map<String,Object>> revenue() {

        List<Order> list = repo.findByStatus("DELIVERED");

        Map<String, BigDecimal> map = new HashMap<>();

        for (Order o : list) {
            String name = o.getCustomer().getFullName();

            map.put(name,
                    map.getOrDefault(name, BigDecimal.ZERO)
                            .add(o.getTotalAmount()));
        }

        List<Map<String,Object>> res = new ArrayList<>();

        for (String k : map.keySet()) {
            res.add(Map.of(
                    "customerName", k,
                    "totalRevenue", map.get(k)
            ));
        }

        return res;
    }
}