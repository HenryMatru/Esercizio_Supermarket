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

import com.exercice.supermarket.controllers.ClientController;
import com.exercice.supermarket.dto.ClientDTO;
import com.exercice.supermarket.dto.ProductDTO;
import com.exercice.supermarket.dto.TicketDTO;
import com.exercice.supermarket.mappers.ClientMapper;
import com.exercice.supermarket.mappers.TicketMapper;
import com.exercice.supermarket.models.Client;
import com.exercice.supermarket.services.ClientService;

@RestController
@RequestMapping("/client")
public class ClientControllerImpl implements ClientController {
	
	private final ClientService clientService;
	
	private final ClientMapper clientMapper;
	
	private final TicketMapper ticketMapper;
	
	@Autowired
	public ClientControllerImpl(ClientService clientService,
								ClientMapper clientMapper,
								TicketMapper ticketMapper) {
		this.clientService = clientService;
		this.clientMapper = clientMapper;
		this.ticketMapper = ticketMapper;
	}
	
	@Override
	@PostMapping("/insert")
	public ResponseEntity<ClientDTO> save(@RequestBody ClientDTO clientDTO) {
		Client client = this.clientMapper.asEntity(clientDTO);
		return new ResponseEntity<>(this.clientMapper.asDTO(this.clientService.save(client)),
				                    HttpStatus.CREATED);
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable("id") Long id) {
		if (this.clientService.findById(id).isPresent()) {
			Client client = this.clientService.findById(id).get();
			return new ResponseEntity<>(this.clientMapper.asDTO(client), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@Override
	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll() {
		return new ResponseEntity<>(this.clientMapper.asDTOList(this.clientService.findAll()),
				                    HttpStatus.OK);
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<ClientDTO> update(@RequestBody ClientDTO clientDTO, @PathVariable("id") Long id) {
		if (this.clientService.findById(id).isPresent()) {
			Client client = this.clientService.update(this.clientMapper.asEntity(clientDTO), id);
			return new ResponseEntity<>(this.clientMapper.asDTO(client), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<ClientDTO> delete(@PathVariable("id") Long id) {
		if (this.clientService.findById(id).isPresent()) {
			ClientDTO clientDTO = this.clientMapper.asDTO(this.clientService.findById(id).get());
			this.clientService.deleteById(id);
			return new ResponseEntity<>(clientDTO, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@Override
	@PutMapping("/shopping/{id}")
	public ResponseEntity<TicketDTO> shopping(List<ProductDTO> productDTOS, Long id) {
		return new ResponseEntity<>(this.ticketMapper.asDTO(this.clientService.shopping(productDTOS, id)), HttpStatus.CREATED);
	}

}
