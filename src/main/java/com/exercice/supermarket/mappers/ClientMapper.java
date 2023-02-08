package com.exercice.supermarket.mappers;

import org.mapstruct.Mapper;

import com.exercice.supermarket.dto.ClientDTO;
import com.exercice.supermarket.models.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper extends GenericMapper<Client, ClientDTO> {

}
