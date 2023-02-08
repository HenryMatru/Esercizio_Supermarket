package com.exercice.supermarket.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.exercice.supermarket.dto.ClientDTO;
import com.exercice.supermarket.dto.ProductDTO;
import com.exercice.supermarket.dto.TicketDTO;

public interface ClientController {
	
	ResponseEntity<ClientDTO> save(ClientDTO clientDTO);
	
	ResponseEntity<ClientDTO> findById(Long id);
	
	ResponseEntity<List<ClientDTO>> findAll();
	
	ResponseEntity<ClientDTO> update(ClientDTO clientDTO, Long id);
	
	ResponseEntity<ClientDTO> delete(Long id);
	
	ResponseEntity<TicketDTO> shopping(List<ProductDTO> productDTOS, Long id);
	
}
