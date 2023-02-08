package com.exercice.supermarket.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercice.supermarket.dao.ClientRepository;
import com.exercice.supermarket.dao.ProductRepository;
import com.exercice.supermarket.dao.TicketRepository;
import com.exercice.supermarket.dto.ProductDTO;
import com.exercice.supermarket.mappers.ProductMapper;
import com.exercice.supermarket.models.Client;
import com.exercice.supermarket.models.Product;
import com.exercice.supermarket.models.Ticket;
import com.exercice.supermarket.services.ClientService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

	private final ClientRepository clientRepository;
	
	private final TicketRepository ticketRepository;
	
	private final ProductRepository productRepository;
	
	private final ProductMapper productMapper;
	
	@Autowired
	public ClientServiceImpl(ClientRepository clientRepository,
							 TicketRepository ticketRepository,
							 ProductRepository productRepository,
							 ProductMapper productMapper) {
		this.clientRepository = clientRepository;
		this.ticketRepository = ticketRepository;
		this.productRepository = productRepository;
		this.productMapper = productMapper;
	}
	
	@Override
	public Client save(Client entity) {
		return this.clientRepository.save(entity);
	}

	@Override
	public List<Client> save(List<Client> entities) {
		return this.clientRepository.saveAll(entities);
	}

	@Override
	public Optional<Client> findById(Long id) {
		return this.clientRepository.findById(id);
	}

	@Override
	public List<Client> findAll() {
		return this.clientRepository.findAll();
	}

	@Override
	public Client update(Client entity, Long id) {
		if (this.findById(id).isPresent()) {
			entity.setId(id);
			this.save(entity);
		}
		return null;
	}

	@Override
	public void deleteById(Long id) {
		if (this.findById(id).isPresent()) {
			Client client = this.findById(id).get();
			client.setTickets(null);
			for (Ticket ticket : this.ticketRepository.findAll()) {
				if (ticket.getClient().getId() == id) {
					ticket.setClient(null);
				}
			}
			this.clientRepository.deleteById(id);
		}
	}

	@Override
	public Ticket shopping(List<ProductDTO> productDTOS, Long id) {
		Ticket ticket = new Ticket();
		
		for (ProductDTO productDTO : productDTOS) {
			if (this.productRepository.findById(this.productMapper.asEntity(productDTO).getId()).isPresent()) {
				Product product = this.productMapper.asEntity(productDTO);
				ticket.getProducts().add(product);
				ticket.setPrice(ticket.getPrice() + product.getPrice());
			} else {
				return null;
			}
		}
		
		if (this.findById(id).isEmpty()) {
			return null;
		}
		
		Client client = this.findById(id).get();
		client.getTickets().add(ticket);
		this.save(client);
		
		return this.ticketRepository.save(ticket);
	}

}
