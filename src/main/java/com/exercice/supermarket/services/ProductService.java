package com.exercice.supermarket.services;

import java.util.List;

import com.exercice.supermarket.models.Product;
import com.exercice.supermarket.models.Ticket;

public interface ProductService extends GenericService<Product, Long> {
	
	Ticket addProductsToTicket(List<Product> products, Long id);
	
}
