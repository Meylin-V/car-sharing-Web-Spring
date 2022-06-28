package com.example.carsharing.controller;

import com.example.carsharing.entity.Car;
import com.example.carsharing.entity.Client;
import com.example.carsharing.repository.CarRepository;
import com.example.carsharing.repository.ClientRepository;
import com.example.carsharing.service.ClientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ClientController {

  private final ClientRepository clientRepository;
  private final ClientService clientService;
  private final CarRepository carRepository;

  @Autowired
  public ClientController(
      ClientRepository clientRepository,
      CarRepository carRepository, ClientService clientService) {
    this.clientRepository = clientRepository;
    this.carRepository = carRepository;
    this.clientService = clientService;
  }

  @GetMapping("/manager-func")
  public String showAllClients(Model model) {
    List<Client> result = clientService.sortById(clientRepository.findAll());
    model.addAttribute("clients", result);
    return "view-clients";
  }

  @GetMapping("/delete/{id}")
  public String deleteExpression(@PathVariable("id") int id) {
    Client client = clientRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("invalid client id"));
    if (carRepository.existsCarByClients_Id(id)) {
      Car car = carRepository.findByClients_Id(id);
      car.setAvailable(true);
      car.setClients(null);
      carRepository.save(car);
    }
    clientRepository.delete(client);
    return "redirect:/manager-func";
  }
}
