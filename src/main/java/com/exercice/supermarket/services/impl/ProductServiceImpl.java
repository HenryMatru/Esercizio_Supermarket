package com.exercice.supermarket.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercice.supermarket.dao.ProductRepository;
import com.exercice.supermarket.dao.TicketRepository;
import com.exercice.supermarket.models.Product;
import com.exercice.supermarket.models.Ticket;
import com.exercice.supermarket.services.ProductService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	
	private final TicketRepository ticketRepository;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository, TicketRepository ticketRepository) {
		this.productRepository = productRepository;
		this.ticketRepository = ticketRepository;
	}
	
	@Override
	public Product save(Product entity) {
		return this.productRepository.save(entity);
	}

	@Override
	public List<Product> save(List<Product> entities) {
		return this.productRepository.saveAll(entities);
	}
	
	@Override
	public Optional<Product> findById(Long id) {
		return this.productRepository.findById(id);
	}
	
	@Override
	public List<Product> findAll() {
		return this.productRepository.findAll();
	}

	@Override
	public Product update(Product entity, Long id) {
		if (this.findById(id).isPresent()) {
			entity.setId(id);
			this.save(entity);
		}
		return null;
	}
	
	@Override
	public Ticket addProductsToTicket(List<Product> products, Long id) {
		if (this.ticketRepository.findById(id).isPresent()) {
			Ticket ticket = this.ticketRepository.findById(id).get();
			for (Product product : products) {
				if (this.productRepository.findById(id).isPresent()) {
					ticket.getProducts().add(product);
				}
			}
			return ticket;
		}
		return null;
	}
	
	@Override
	public void deleteById(Long id) {
		this.productRepository.deleteById(id);
	}

}
