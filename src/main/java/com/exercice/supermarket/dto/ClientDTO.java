package com.exercice.supermarket.dto;

import java.util.HashSet;
import java.util.Set;

import com.exercice.supermarket.models.Ticket;
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
public class ClientDTO {
	
	private Long id_client;
	private String name;
	private String surname;
	private Set<Ticket> tickets = new HashSet<>();
	
}
