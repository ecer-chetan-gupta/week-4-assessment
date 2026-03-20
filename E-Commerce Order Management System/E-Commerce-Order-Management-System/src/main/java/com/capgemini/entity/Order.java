package com.capgemini.entity;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    private String productName;

    private int quantity;

    private BigDecimal pricePerUnit;

    private BigDecimal totalAmount;

    private String status;

    @Version
    private int version;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}