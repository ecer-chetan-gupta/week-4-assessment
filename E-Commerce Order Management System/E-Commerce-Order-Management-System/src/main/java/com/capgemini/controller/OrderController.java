package com.capgemini.controller;

import com.capgemini.dto.OrderDTO;
import com.capgemini.entity.Order;
import com.capgemini.service.OrderServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderServiceImpl service;

    @PostMapping("/customers/{id}/orders")
    public Order create(@PathVariable int id,
                        @Valid @RequestBody OrderDTO dto) {
        return service.place(id, dto);
    }

    @PatchMapping("/orders/{id}/cancel")
    public Order cancel(@PathVariable int id) {
        return service.cancel(id);
    }

    @GetMapping("/customers/revenue-summary")
    public List<Map<String,Object>> revenue() {
        return service.revenue();
    }
}