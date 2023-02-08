package com.exercice.supermarket.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercice.supermarket.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
