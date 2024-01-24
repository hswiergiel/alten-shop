package com.example.altenshop.dto;

import java.math.BigDecimal;

import com.example.altenshop.model.EProductStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRequest {

    private String code;

    private String name;

    private String description;

    private String image;

    private BigDecimal price;

    private String category;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private EProductStatus inventoryStatus;

    private Integer rating;
}
