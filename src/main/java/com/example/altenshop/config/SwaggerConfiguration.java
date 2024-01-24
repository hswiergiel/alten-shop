package com.example.altenshop.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customedOpenAPI() {
        return new OpenAPI().info(new Info().title("API AltenShop").version("v1.0"));
    }
}
