package com.exercice.supermarket.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.exercice.supermarket.dto.ProductDTO;
import com.exercice.supermarket.dto.TicketDTO;

public interface ProductController {
	
	ResponseEntity<ProductDTO> save(ProductDTO productDTO);

    ResponseEntity<ProductDTO> findById(Long id);

    ResponseEntity<List<ProductDTO>> all();

    ResponseEntity<ProductDTO> update(ProductDTO productDTO, Long id);
    
    ResponseEntity<TicketDTO> addProductsToTicket(List<ProductDTO> productDTOS, Long id);
    
    ResponseEntity<ProductDTO> delete(Long id);
	
}
