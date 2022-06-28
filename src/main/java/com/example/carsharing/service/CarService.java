package com.example.carsharing.service;

import com.example.carsharing.entity.Car;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class CarService {

  public List<Car> sortById(List<Car> cars) {
    return cars.stream().sorted(Comparator.comparingInt(Car::getId)).collect(Collectors.toList());
  }

  public List<Car> sortByPrice(List<Car> cars) {
    return cars.stream().sorted(Comparator.comparingInt(Car::getPrice))
        .collect(Collectors.toList());
  }

  public List<Car> sortByAv(List<Car> cars) {
    return cars.stream().sorted(Comparator.comparing(Car::getAvailable))
        .collect(Collectors.toList());
  }
}
