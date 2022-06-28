package com.example.carsharing.repository;

import com.example.carsharing.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

  Client findByPhone(String phone);
}