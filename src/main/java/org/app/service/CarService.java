package org.app.service;

import java.util.List;
import java.util.Optional;

import org.app.entity.Car;
import org.app.entitydto.CarDto;

public interface CarService {
    long saveCar(CarDto carDto);
    List<Car> getAll();
    String addMulRec(List<CarDto> records);
    Optional<Car> getCarById(Long id);
    Car deleteCar(Long id);
    Car updateCar(Long id, CarDto carDto);
    List<Car> getCarsBySpeedRange(short minSpeed, short maxSpeed);
}
