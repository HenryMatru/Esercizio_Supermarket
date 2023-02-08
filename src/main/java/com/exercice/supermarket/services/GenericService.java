package com.exercice.supermarket.services;

import java.util.List;
import java.util.Optional;

public interface GenericService<E, M> {

	E save(E entity);

    List<E> save(List<E> entities);

    Optional<E> findById(M id);

    List<E> findAll();

    E update(E entity, M id);
    
    void deleteById(M id);
	
}
