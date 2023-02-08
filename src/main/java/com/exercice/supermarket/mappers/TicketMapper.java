package com.exercice.supermarket.mappers;

import org.mapstruct.Mapper;

import com.exercice.supermarket.dto.TicketDTO;
import com.exercice.supermarket.models.Ticket;

@Mapper(componentModel = "spring")
public interface TicketMapper extends GenericMapper<Ticket, TicketDTO> {

}
