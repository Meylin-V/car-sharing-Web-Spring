package com.example.carsharing.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

import com.example.carsharing.entity.Car;
import com.example.carsharing.entity.Client;
import com.example.carsharing.entity.Owner;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarRepositoryTest {

  @MockBean
  private CarRepository carRepository;

  @Test
  public void testFindByClients_Id() {
    Owner owner = new Owner(2, "Ivan", "Ivanov", "0573212121");
    Client client = new Client(12, "Nami", "San", "0955758788");
    given(this.carRepository.findByClients_Id(anyInt()))
        .willReturn(new Car(12, "Toyota Corolla", 180, owner, client, false));
    Car car = carRepository.findByClients_Id(12);
    assertThat(car.getId()).isEqualTo(12);
  }

  @Test
  public void testExistsCarByClients_Id_True() {
    given(this.carRepository.existsCarByClients_Id(anyInt()))
        .willReturn(true);
    boolean result = carRepository.existsCarByClients_Id(12);
    assertThat(result);
  }

  @Test
  public void testExistsCarByClients_Id_False() {
    given(this.carRepository.existsCarByClients_Id(anyInt()))
        .willReturn(true);
    boolean result = carRepository.existsCarByClients_Id(33);
    assertThat(!result);
  }

  @Test
  public void testFindByAvailableTrue() {
    Owner owner4 = new Owner(4, "Valentin", "Popov", "0667677667");
    Owner owner6 = new Owner(6, "Liliya", "Parfenova", "0333033333");
    Car car15 = new Car(15, "Ford F-Series", 135, owner4, null, true);
    Car car17 = new Car(17, "Zhigul Kopeika", 10, owner6, null, true);
    List<Car> cars = new ArrayList<>();
    cars.add(car15);
    cars.add(car17);
    given(this.carRepository.findByAvailableTrue())
        .willReturn(cars);
    List<Car> carsFromDB = carRepository.findByAvailableTrue();
    assertThat(cars).isEqualTo(carsFromDB);
  }
}
