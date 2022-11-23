package com.phoodora.restapi.repositories;

import com.phoodora.restapi.models.Restaurant;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {
    Restaurant findByName(String name);
}
