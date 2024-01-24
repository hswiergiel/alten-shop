package com.example.altenshop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.altenshop.controller.ProductController;
import com.example.altenshop.model.ProductEntity;
import com.example.altenshop.service.ProductService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void shouldReturnProducts() throws Exception {
        mockMvc.perform(get("/products"))
               .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnProductById() throws Exception {
        when(productService.getProductById(1)).thenReturn(new ProductEntity());
        
        mockMvc.perform(get("/products/1"))
               .andExpect(status().isOk());
    }
}
