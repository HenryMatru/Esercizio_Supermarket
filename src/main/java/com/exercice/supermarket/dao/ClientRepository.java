package com.exercice.supermarket.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercice.supermarket.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
