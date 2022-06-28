package com.example.carsharing.controller;

import com.example.carsharing.entity.Car;
import com.example.carsharing.entity.Owner;
import com.example.carsharing.repository.CarRepository;
import com.example.carsharing.repository.OwnerRepository;
import com.example.carsharing.service.OwnerService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OwnerController {

  private final OwnerRepository ownerRepository;
  private final OwnerService ownerService;
  private final CarRepository carRepository;

  public OwnerController(OwnerRepository ownerRepository,
      OwnerService ownerService, CarRepository carRepository) {
    this.ownerRepository = ownerRepository;
    this.ownerService = ownerService;
    this.carRepository = carRepository;
  }

  @GetMapping("/owner-func")
  public String showAllClients(Model model) {
    List<Owner> result = ownerService.sortById(ownerRepository.findAll());
    model.addAttribute("owners", result);
    return "view-owners";
  }

  @GetMapping("/edit_car/{id}")
  public String showCarPage(@PathVariable("id") int id, Model model) {
    Owner owner = ownerRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("invalid car id"));
    model.addAttribute("owner", owner);
    return "new-car";
  }

  @PostMapping("/update_car/{id}")
  public String updateExpression(@PathVariable("id") int id,
      @RequestParam("model") String model, @RequestParam("price") int price) {
    try {
      Owner owner = ownerRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("invalid car id"));
      Car car = new Car(0, model, price, owner, null, true);
      carRepository.save(car);
    } catch (Exception e) {
      throw new RuntimeException();
    }
    return "redirect:/owner-func";
  }

  @GetMapping("/new_owner")
  public String showOwnerPage() {
    return "new-owner";
  }

  @PostMapping("/add_new_owner")
  public String updateExpression(@RequestParam("firstName") String firstName,
      @RequestParam("lastName") String lastName, @RequestParam("phone") String phone) {
    try {
      Owner owner = new Owner(0, firstName, lastName, phone);
      ownerRepository.save(owner);
    } catch (Exception e) {
      throw new RuntimeException();
    }
    return "redirect:/owner-func";
  }
}
