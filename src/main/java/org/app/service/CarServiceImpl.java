package org.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.app.entity.Car;
import org.app.entitydto.CarDto;
import org.app.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public long saveCar(CarDto carDto) {
        Car car = convertToEntity(carDto);
        Car savedCar = carRepository.save(car);
        return savedCar.getCarid();
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public String addMulRec(List<CarDto> records) {
        List<Car> cars = records.stream()
                                .map(this::convertToEntity)
                                .collect(Collectors.toList());
        carRepository.saveAll(cars);
        return records.size() + " records added";
    }

    @Override
    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public Car deleteCar(Long id) {
        Optional<Car> car = carRepository.findById(id);
        car.ifPresent(c -> carRepository.deleteById(id));
        return car.orElse(null);
    }

    @Override
    public Car updateCar(Long id, CarDto carDto) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            Car car = convertToEntity(carDto);
            car.setCarid(id);  // Ensure the ID remains the same
            return carRepository.save(car);
        }
        return null;
    }

    private Car convertToEntity(CarDto carDto) {
        return Car.builder()
                  .carid(carDto.getCarid())
                  .carName(carDto.getCarName())
                  .maxSpeed(carDto.getMaxSpeed())
                  .engineModel(carDto.getEngineModel())
                  .build();
    }
    @Override
    public List<Car> getCarsBySpeedRange(short minSpeed, short maxSpeed) {
        return carRepository.findByMaxSpeedBetween(minSpeed, maxSpeed);
    }

}
