package com.example.carsharing.service;

import com.example.carsharing.entity.Owner;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OwnerServiceTest extends TestCase {

  private OwnerService ownerService;
  private List<Owner> unsortedOwners;

  @BeforeEach
  public void setUp() {
    ownerService = new OwnerService();
    unsortedOwners = createOwners("unSort");
  }

  @Test
  public void testSortById() {
    List<Owner> sortedOwners = createOwners("");
    Assertions.assertEquals(sortedOwners, ownerService.sortById(unsortedOwners));
  }

  private List<Owner> createOwners(String mark) {
    List<Owner> ownerList = new ArrayList<>();
    Owner owner1 = new Owner(1, "client1", "client1", "000");
    Owner owner2 = new Owner(4, "client2", "client2", "111");
    Owner owner3 = new Owner(2, "client3", "client3", "222");
    Owner owner4 = new Owner(3, "client4", "client4", "333");
    if (mark.equals("unSort")) {
      ownerList.add(owner1);
      ownerList.add(owner2);
      ownerList.add(owner3);
      ownerList.add(owner4);
    } else {
      ownerList.add(owner1);
      ownerList.add(owner3);
      ownerList.add(owner4);
      ownerList.add(owner2);
    }
    return ownerList;
  }
}
