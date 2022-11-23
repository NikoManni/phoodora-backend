package com.phoodora.restapi.repositories;

import com.phoodora.restapi.models.Product;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    
}
