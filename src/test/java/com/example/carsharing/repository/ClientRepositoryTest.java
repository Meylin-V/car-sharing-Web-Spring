package com.example.carsharing.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.example.carsharing.entity.Client;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientRepositoryTest {

  @MockBean
  private ClientRepository clientRepository;

  @Test
  public void testFindByPhone() {
    given(this.clientRepository.findByPhone(any()))
        .willReturn(new Client(13, "aaa", "aaa", "aaa"));
    Client client = clientRepository.findByPhone("aaa");
    assertThat(client.getId()).isEqualTo(13);
  }
}
