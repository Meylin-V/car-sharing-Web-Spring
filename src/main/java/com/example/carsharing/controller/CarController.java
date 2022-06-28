package com.example.carsharing.controller;

import com.example.carsharing.entity.Car;
import com.example.carsharing.entity.Client;
import com.example.carsharing.repository.CarRepository;
import com.example.carsharing.repository.ClientRepository;
import com.example.carsharing.service.CarService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CarController {

  private final CarRepository carRepository;
  private final CarService carService;
  private final ClientRepository clientRepository;

  @Autowired
  public CarController(CarRepository carRepository,
      ClientRepository clientRepository, CarService carService) {
    this.carRepository = carRepository;
    this.clientRepository = clientRepository;
    this.carService = carService;
  }

  @GetMapping("/client-func")
  public String showAvailable(Model model) {
    List<Car> result = carService.sortById(carRepository.findByAvailableTrue());
    model.addAttribute("cars", result);
    return "view-cars-av";
  }

  @GetMapping("/cars-all")
  public String showAllCars(Model model) {
    List<Car> result = carService.sortById(carRepository.findAll());
    model.addAttribute("cars", result);
    return "view-cars-all";
  }

  @GetMapping("/edit/{id}")
  public String showClientPage(@PathVariable("id") int id, Model model) {
    Car car = carRepository.findById(id).orElseThrow(()->new IllegalArgumentException("invalid car id"));
    model.addAttribute("car", car);
    return "new-client";
  }

  @PostMapping("/update/{id}")
  public String updateExpression(@PathVariable("id") int id,
      @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
      @RequestParam("phone") String phone) {
    try {
      Client client = new Client(0, firstName, lastName, phone);
      clientRepository.save(client);
      Car car = carRepository.findById(id).orElseThrow(()->new IllegalArgumentException("invalid car id"));
      Client client1 = clientRepository.findByPhone(phone);
      car.setClients(client1);
      car.setAvailable(false);
      carRepository.save(car);
    } catch (Exception ignored) {}
    return "redirect:/client-func";
  }

  @GetMapping("/order_price_av")
  public String showAvailableByPrice(Model model) {
    List<Car> result = carService.sortByPrice(carRepository.findByAvailableTrue());
    model.addAttribute("cars", result);
    return "view-cars-av";
  }

  @GetMapping("/order_price_all")
  public String showAllByPrice(Model model) {
    List<Car> result = carService.sortByPrice(carRepository.findAll());
    model.addAttribute("cars", result);
    return "view-cars-all";
  }

  @GetMapping("/order_av_all")
  public String showAllByAv(Model model) {
    List<Car> result = carService.sortByAv(carRepository.findAll());
    model.addAttribute("cars", result);
    return "view-cars-all";
  }
}
