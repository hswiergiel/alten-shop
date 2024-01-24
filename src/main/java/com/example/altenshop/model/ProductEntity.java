package com.example.altenshop.model;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "product")
@ToString
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    private String description;

    private String image;

    private BigDecimal price;

    private String category;

    private Integer quantity;

    @Column(name = "inventory_status")
    @Enumerated(EnumType.STRING)
    private EProductStatus inventoryStatus;

    private Integer rating;
}
