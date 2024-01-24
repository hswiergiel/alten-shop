package com.example.altenshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.altenshop.model.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    Optional<ProductEntity> findByCode(String code);
}
