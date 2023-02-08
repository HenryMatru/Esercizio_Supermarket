package com.exercice.supermarket.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.exercice.supermarket.dto.TicketDTO;

public interface TicketController {
	
	ResponseEntity<TicketDTO> save(TicketDTO ticketDTO);
	
	ResponseEntity<TicketDTO> findById(Long id);
	
	ResponseEntity<List<TicketDTO>> findAll();
	
	ResponseEntity<TicketDTO> update(TicketDTO ticketDTO, Long id);
	
	ResponseEntity<TicketDTO> delete(Long id);

}
