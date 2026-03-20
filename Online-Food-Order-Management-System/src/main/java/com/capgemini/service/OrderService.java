package com.capgemini.service;


import com.capgemini.entity.Order;
import java.util.List;
import java.util.Map;

public interface OrderService {

    Map<String, Object> createOrder(Order order);

    Order getOrderById(int id);

    List<Order> getAllOrders();

    Map<String, String> updateOrder(int id, Order order);

    Map<String, String> cancelOrder(int id);
}