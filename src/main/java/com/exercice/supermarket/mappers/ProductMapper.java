package com.exercice.supermarket.mappers;

import org.mapstruct.Mapper;

import com.exercice.supermarket.dto.ProductDTO;
import com.exercice.supermarket.models.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper extends GenericMapper<Product, ProductDTO> {

}
