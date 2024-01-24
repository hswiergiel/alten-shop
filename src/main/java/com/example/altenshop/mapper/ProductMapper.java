package com.example.altenshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.altenshop.dto.ProductRequest;
import com.example.altenshop.model.ProductEntity;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    ProductEntity createProductEntity(ProductRequest productRequest);

    @Mapping(target = "id", ignore = true)
    void updateProductEntity(ProductRequest productRequest, @MappingTarget ProductEntity product);
}
