package com.example.altenshop.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.altenshop.dto.ProductRequest;
import com.example.altenshop.model.ProductEntity;
import com.example.altenshop.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "Récupère tous les produits")
    @ApiResponse(responseCode = "200", description = "Récupération de tous les produits réussie")
    @GetMapping
    public ResponseEntity<List<ProductEntity>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @Operation(summary = "Récupérer un produit par son id")
    @ApiResponse(responseCode = "200", description = "Récupération réussie du produit")
    @ApiResponse(responseCode = "404", description = "Produit non trouvé")
    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @Operation(summary = "Crée un nouveau produit")
    @ApiResponse(responseCode = "200", description = "Création réussie du produit")
    @PostMapping
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @Operation(summary = "Met à jour un produit")
    @ApiResponse(responseCode = "200", description = "Mise à jour réussie ")
    @ApiResponse(responseCode = "404", description = "Produit non trouvé")
    @PatchMapping("/{id}")
    public ResponseEntity<ProductEntity> updateProduct(@PathVariable Integer id,
            @RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.updateProduct(id, productRequest));
    }

    @Operation(summary = "Supprime un produit")
    @ApiResponse(responseCode = "204", description = "Suppression réussie du produit")
    @ApiResponse(responseCode = "404", description = "Produit non trouvé")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
