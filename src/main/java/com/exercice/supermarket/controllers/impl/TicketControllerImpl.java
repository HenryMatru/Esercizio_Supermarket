package com.exercice.supermarket.controllers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exercice.supermarket.controllers.TicketController;
import com.exercice.supermarket.dto.TicketDTO;
import com.exercice.supermarket.mappers.TicketMapper;
import com.exercice.supermarket.models.Ticket;
import com.exercice.supermarket.services.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketControllerImpl implements TicketController {
	
	private final TicketService ticketService;
	
	private final TicketMapper ticketMapper;

	@Autowired
	public TicketControllerImpl(TicketService ticketService,
								TicketMapper ticketMapper) {
		this.ticketService = ticketService;
		this.ticketMapper = ticketMapper;
	}
	
	@Override
	@PostMapping("/insert")
	public ResponseEntity<TicketDTO> save(@RequestBody TicketDTO ticketDTO) {
		Ticket ticket = this.ticketMapper.asEntity(ticketDTO);
		return new ResponseEntity<>(this.ticketMapper.asDTO(this.ticketService.save(ticket)),
									HttpStatus.CREATED);
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<TicketDTO> findById(@PathVariable("id") Long id) {
		if (this.ticketService.findById(id).isPresent()) {
			Ticket ticket = this.ticketService.findById(id).get();
			TicketDTO ticketDTO = this.ticketMapper.asDTO(ticket);
			return new ResponseEntity<>(ticketDTO, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@Override
	@GetMapping
	public ResponseEntity<List<TicketDTO>> findAll() {
		return new ResponseEntity<>(this.ticketMapper.asDTOList(this.ticketService.findAll()),
							        HttpStatus.OK);
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<TicketDTO> update(@RequestBody TicketDTO ticketDTO, @PathVariable("id") Long id) {
		if (this.ticketService.findById(id).isPresent()) {
			Ticket ticket = this.ticketService.update(this.ticketMapper.asEntity(ticketDTO), id);
			return new ResponseEntity<>(this.ticketMapper.asDTO(ticket), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<TicketDTO> delete(@PathVariable("id") Long id) {
		if (this.ticketService.findById(id).isPresent()) {
			TicketDTO ticketDTO = this.ticketMapper.asDTO(this.ticketService.findById(id).get());
			this.ticketService.deleteById(id);
			return new ResponseEntity<>(ticketDTO, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
