package com.exercice.supermarket.services;

import java.util.List;

import com.exercice.supermarket.dto.ProductDTO;
import com.exercice.supermarket.models.Client;
import com.exercice.supermarket.models.Ticket;

public interface ClientService extends GenericService<Client, Long> {

	Ticket shopping(List<ProductDTO> productDTOS, Long id);
	
}
