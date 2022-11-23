package com.phoodora.restapi.repositories;

import com.phoodora.restapi.models.Order;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

}
