package com.example.altenshop;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.altenshop.mapper.ProductMapper;
import com.example.altenshop.model.ProductEntity;
import com.example.altenshop.repository.ProductRepository;
import com.example.altenshop.service.ProductService;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.Optional;

@SpringBootTest
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @Test
    public void shouldGetProductById() {
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(new ProductEntity()));

        productService.getProductById(1);

        verify(productRepository, times(1)).findById(anyInt());
    }
}
