package com.example.altenshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.altenshop.dto.ProductRequest;
import com.example.altenshop.mapper.ProductMapper;
import com.example.altenshop.model.ProductEntity;
import com.example.altenshop.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductEntity> getProducts() {
        return productRepository.findAll();
    }

    public ProductEntity getProductById(@NonNull Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produit non trouvé avec l\'id " + id));
    }

    public ProductEntity createProduct(ProductRequest productRequest) {
        Optional<ProductEntity> productEntity = productRepository.findByCode(productRequest.getCode());

        if (productEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Produit déjà présent avec le code " + productRequest.getCode());
        }
        ProductEntity product = productMapper.createProductEntity(productRequest);
        if (product == null) {
            throw new IllegalStateException("Erreur dans la requête");
        }
        return productRepository.save(product);
    }

    public ProductEntity updateProduct(@NonNull Integer id, ProductRequest productRequest) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produit non trouvé avec l\'id " + id));
        productMapper.updateProductEntity(productRequest, product);
        if (product == null) {
            throw new IllegalStateException("Erreur dans la requête");
        }
        return productRepository.save(product);
    }

    public void deleteProduct(@NonNull Integer id) {
        try {
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException("Produit non trouvé avec l'id " + id);
        }
    }
}
