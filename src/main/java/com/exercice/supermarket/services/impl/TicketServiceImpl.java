package com.exercice.supermarket.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercice.supermarket.dao.ClientRepository;
import com.exercice.supermarket.dao.TicketRepository;
import com.exercice.supermarket.models.Ticket;
import com.exercice.supermarket.services.TicketService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

	private final TicketRepository ticketRepository;
	
	private final ClientRepository clientRepository;
	
	@Autowired
	public TicketServiceImpl(TicketRepository ticketRepository,
							 ClientRepository clientRepository) {
		this.ticketRepository = ticketRepository;
		this.clientRepository = clientRepository;
	}
	
	@Override
	public Ticket save(Ticket entity) {
		
		if (entity.getClient() != null) {
			entity.setClient(this.clientRepository.findById(entity.getClient().getId()).get());
		}
		/*
		Set<Product> validProducts = new HashSet<>();
		for (Product productInEntity : entity.getProducts()) {
			if (this.productRepository.findById(productInEntity.getId()).isPresent()) {
				validProducts.add(this.productRepository.findById(productInEntity.getId()).get());
			}
		}
		
		entity.setProducts(validProducts);
		
		Ticket ticket = this.ticketRepository.save(entity);
		
		for (Product product : ticket.getProducts()) {
			System.out.println(product.getId());
			System.out.println(product.getName());
			System.out.println(product.getPrice());
			System.out.println(product.getExpiration());
			System.out.println();
		}
		*/
		return this.ticketRepository.save(entity);
	}

	@Override
	public List<Ticket> save(List<Ticket> entities) {
		return this.ticketRepository.saveAll(entities);
	}

	@Override
	public Optional<Ticket> findById(Long id) {
		return this.ticketRepository.findById(id);
	}

	@Override
	public List<Ticket> findAll() {
		return this.ticketRepository.findAll();
	}

	@Override
	public Ticket update(Ticket entity, Long id) {
		if (this.findById(id).isPresent()) {
			entity.setId(id);
			this.save(entity);
		}
		return null;
	}

	@Override
	public void deleteById(Long id) {
		if (this.ticketRepository.findById(id).isPresent()) {
			Ticket ticket = this.ticketRepository.findById(id).get();
			ticket.setClient(null);
			ticket.setProducts(new HashSet<>());
			this.update(ticket, id);
			this.ticketRepository.deleteById(id);
		}
	}

}
