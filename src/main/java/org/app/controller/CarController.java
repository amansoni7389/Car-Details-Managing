package org.app.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.app.entity.Car;
import org.app.entitydto.CarDto;
import org.app.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
public class CarController {

    @Autowired
    private CarService service;

    @PostMapping("/saveNewCar")
    public ResponseEntity<Long> saveCar(@Valid @RequestBody CarDto carDto) {
        return new ResponseEntity<>(service.saveCar(carDto), HttpStatus.CREATED);
    }

    @GetMapping("/allRecords")
    public ResponseEntity<List<Car>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping("/addMultipleRecords")
    public ResponseEntity<String> addMultipleRecords(@RequestBody List<CarDto> carDtos) {
        return new ResponseEntity<>(service.addMulRec(carDtos), HttpStatus.CREATED);
    }

    @GetMapping("/getCar/{carid}")
    public ResponseEntity<Car> getCarById(@PathVariable(name = "carid") Long id) {
        Optional<Car> car = service.getCarById(id);
        if (car.isPresent()) {
            return new ResponseEntity<>(car.get(), HttpStatus.OK);
        } else {
            throw new NoSuchElementException("Record not found");
        }
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable Long id) {
        Car car = service.deleteCar(id);
        if (car != null) {
            return new ResponseEntity<>(car, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/carUpdate/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody CarDto carDto) {
        Car updatedCar = service.updateCar(id, carDto);
        if (updatedCar != null) {
            return new ResponseEntity<>(updatedCar, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
