package com.example.carsharing.service;

import com.example.carsharing.entity.Client;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClientServiceTest extends TestCase {

  private ClientService clientService;
  private List<Client> unsortedClients;

  @BeforeEach
  public void setUp() {
    clientService = new ClientService();
    unsortedClients = createClients("unSort");
  }

  @Test
  public void testSortById() {
    List<Client> sortedClients = createClients("");
    Assertions.assertEquals(sortedClients, clientService.sortById(unsortedClients));
  }

  private List<Client> createClients(String mark) {
    List<Client> clientList = new ArrayList<>();
    Client client1 = new Client(1, "client1", "client1", "000");
    Client client2 = new Client(4, "client2", "client2", "111");
    Client client3 = new Client(2, "client3", "client3", "222");
    Client client4 = new Client(3, "client4", "client4", "333");
    if (mark.equals("unSort")) {
      clientList.add(client1);
      clientList.add(client2);
      clientList.add(client3);
      clientList.add(client4);
    } else {
      clientList.add(client1);
      clientList.add(client3);
      clientList.add(client4);
      clientList.add(client2);
    }
    return clientList;
  }
}
