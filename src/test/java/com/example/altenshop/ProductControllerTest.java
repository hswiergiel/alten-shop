package com.example.altenshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.altenshop.controller.ProductController;
import com.example.altenshop.dto.ProductRequest;
import com.example.altenshop.model.ProductEntity;
import com.example.altenshop.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @BeforeEach
    public void setup() {
        if(webApplicationContext != null)
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldGetProducts() throws Exception {
        mockMvc.perform(get("/products"))
               .andExpect(status().isOk());
    }

    @Test
    public void shouldGetProductById() throws Exception {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setName("Watch");
        productEntity.setCode("f230fh0g3");
        productEntity.setImage("bamboo-watch.jpg");

        when(productService.getProductById(1)).thenReturn(productEntity);

        MediaType jsonMediaType = MediaType.APPLICATION_JSON;

        if(jsonMediaType != null)
        mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", productEntity.getId()))
               .andExpect(status().isOk())
               .andExpect(content().contentType(jsonMediaType))
               .andExpect(jsonPath("$.id").value(1))
               .andExpect(jsonPath("$.name").value("Watch"))
               .andExpect(jsonPath("$.code").value("f230fh0g3"))
               .andExpect(jsonPath("$.image").value("bamboo-watch.jpg"));
        verify(productService).getProductById(1);
    }

    @Test
    void shouldCreateProduct() throws Exception {
        ProductRequest request = new ProductRequest();
        request.setName("Watch");
        request.setCode("f230fh0g3");
        request.setImage("bamboo-watch.jpg");
    
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setName(request.getName());
        productEntity.setCode(request.getCode());
        productEntity.setImage(request.getImage());
    
        when(productService.createProduct(any())).thenReturn(productEntity);
    
        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(request);
        MediaType jsonMediaType = MediaType.APPLICATION_JSON;

        if(requestJson != null && jsonMediaType != null)
        mockMvc.perform(post("/products")
                        .contentType(jsonMediaType)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Watch")) 
                .andExpect(jsonPath("$.code").value("f230fh0g3"))
                .andExpect(jsonPath("$.image").value("bamboo-watch.jpg"));
    }

    @Test
    void shouldUpdateProduct() throws Exception {
        ProductRequest request = new ProductRequest();
        request.setName("Umbrella");
        request.setCode("244wgerg2");


        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(Long.valueOf(1));
        productEntity.setName("Watch");
        productEntity.setCode("f230fh0g3");
        productEntity.setImage("bamboo-watch.jpg");

        when(productService.getProductById(productEntity.getId().intValue())).thenReturn(productEntity);

        ProductEntity updatedProductEntity = new ProductEntity();
        updatedProductEntity.setId(productEntity.getId());
        updatedProductEntity.setName(request.getName());
        updatedProductEntity.setCode(request.getCode());
        updatedProductEntity.setImage(productEntity.getImage());


        when(productService.updateProduct(anyInt(), any())).thenReturn(updatedProductEntity);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(request);
        MediaType jsonMediaType = MediaType.APPLICATION_JSON;

        if(requestJson != null && jsonMediaType != null)
        mockMvc.perform(patch("/products/" + productEntity.getId())
                        .contentType(jsonMediaType)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Umbrella"))
                .andExpect(jsonPath("$.code").value("244wgerg2"))
                .andExpect(jsonPath("$.image").value("bamboo-watch.jpg"));
    }

    @Test
    void shouldDeleteProduct() throws Exception {
        Integer productId = 1; 
    
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(Long.valueOf(productId));
        productEntity.setName("Watch");
        productEntity.setCategory("f230fh0g3");
        productEntity.setImage("bamboo-watch.jpg");
    
        doNothing().when(productService).deleteProduct(productId);
    
        mockMvc.perform(delete("/products/{id}", productId))
                .andExpect(status().isNoContent());
    
        verify(productService).deleteProduct(productId); 
    }
}
