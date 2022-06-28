package com.example.carsharing.service;

import com.example.carsharing.entity.Client;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ClientService {

  public List<Client> sortById(List<Client> clients) {
    return clients.stream().sorted(Comparator.comparingInt(Client::getId))
        .collect(Collectors.toList());
  }
}
