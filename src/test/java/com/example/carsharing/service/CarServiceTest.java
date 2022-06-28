package com.example.carsharing.service;

import com.example.carsharing.entity.Car;
import com.example.carsharing.entity.Client;
import com.example.carsharing.entity.Owner;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CarServiceTest extends TestCase{
  private CarService carService;
  private List<Car> unsortedCars;

  @BeforeEach
  public void setUp() {
    carService = new CarService();
    unsortedCars = createCars("unSort");
  }

  @Test
  public void testSortById() {
    List<Car> sortedCars = createCars("sortID");
    Assertions.assertEquals(sortedCars, carService.sortById(unsortedCars));
  }

  @Test
  public void testSortByPrice() {
    List<Car> sortedCars = createCars("sortPrice");
    Assertions.assertEquals(sortedCars, carService.sortByPrice(unsortedCars));
  }

  @Test
  public void testSortByAv() {
    List<Car> sortedCars = createCars("");
    Assertions.assertEquals(sortedCars, carService.sortByAv(unsortedCars));
  }

  private List<Car> createCars(String mark) {
    List<Car> carsList = new ArrayList<>();
    Owner owner1 = new Owner(1, "AAA", "BBB", "000");
    Owner owner2 = new Owner(2, "BBB", "CCC", "111");
    Client client1 = new Client(1, "client1", "client1", "000");
    Client client2 = new Client(2, "client2", "client2", "111");
    Car car1 = new Car(1, "Kia", 200, owner1, null, true);
    Car car2 = new Car(3, "Volvo", 230, owner1, client1, false);
    Car car3 = new Car(2, "BMW", 250, owner2, client2, false);
    Car car4 = new Car(4, "Reno", 180, owner2, null, true);
    switch (mark) {
      case "unSort" -> {
        carsList.add(car1);
        carsList.add(car2);
        carsList.add(car3);
        carsList.add(car4);
      }
      case "sortID" -> {
        carsList.add(car1);
        carsList.add(car3);
        carsList.add(car2);
        carsList.add(car4);
      }
      case "sortPrice" -> {
        carsList.add(car4);
        carsList.add(car1);
        carsList.add(car2);
        carsList.add(car3);
      }
      default -> {
        carsList.add(car2);
        carsList.add(car3);
        carsList.add(car1);
        carsList.add(car4);
      }
    }
    return carsList;
  }
}
