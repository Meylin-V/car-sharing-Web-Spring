package com.example.carsharing.repository;

import com.example.carsharing.entity.Car;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

  Car findByClients_Id(int id);

  boolean existsCarByClients_Id(int id);

  List<Car> findByAvailableTrue();
}