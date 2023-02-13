package com.exercice.supermarket.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

// import com.exercice.supermarket.models.Ticket;

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
public class ProductDTO {

	private Long id_product;
	private String name;
	private double price;
	private Date expiration;
	// private Ticket ticket;

}
