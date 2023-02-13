package com.exercice.supermarket.dto;

import java.util.Set;

import com.exercice.supermarket.models.Client;
import com.exercice.supermarket.models.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class TicketDTO {

	private Long id_ticket;
	private double price;
	private Client client;
	private Set<Product> products;

}
