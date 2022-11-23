package com.phoodora.restapi.repositories;

import com.phoodora.restapi.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
     Users findByUsername(String username);
}