package com.exercice.supermarket.dto;

import java.util.HashSet;
import java.util.Set;

import com.exercice.supermarket.models.Ticket;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientDTO {
	
	private Long id;
	private String name;
	private String surname;
	private Set<Ticket> tickets = new HashSet<>();
	
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return this.surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Set<Ticket> getTickets() {
		return this.tickets;
	}
	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}
	
}
