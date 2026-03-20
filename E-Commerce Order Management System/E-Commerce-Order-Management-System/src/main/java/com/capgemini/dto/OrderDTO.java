package com.capgemini.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDTO {

    @NotBlank
    private String productName;

    @Min(1)
    private int quantity;

    @DecimalMin("0.01")
    private BigDecimal pricePerUnit;

    @NotBlank
    private String status;
}