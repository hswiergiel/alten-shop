package com.example.altenshop.dto;

import java.math.BigDecimal;

import com.example.altenshop.model.EProductStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRequest {

    public interface OnCreate {}
    public interface OnUpdate {}

    @NotBlank(message = "Le code ne peut pas être vide.", groups = OnCreate.class)
    private String code;

    @NotBlank(message = "Le nom ne peut pas être vide.", groups = OnCreate.class)
    private String name;

    private String description;

    private String image;

    @PositiveOrZero(message = "Le prix doit être positif ou égal à zéro.")
    private BigDecimal price;

    private String category;

    @PositiveOrZero(message = "La quantité doit être positive ou égal à zéro.")
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private EProductStatus inventoryStatus;

    @PositiveOrZero(message = "Le rating doit être positif ou égal à zéro.")
    private Integer rating;
}


