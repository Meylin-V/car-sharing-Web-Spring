package com.example.carsharing.service;

import com.example.carsharing.entity.Owner;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OwnerService {

  public List<Owner> sortById(List<Owner> owners) {
    return owners.stream().sorted(Comparator.comparingInt(Owner::getId))
        .collect(Collectors.toList());
  }
}
