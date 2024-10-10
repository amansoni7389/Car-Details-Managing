package org.app.repository;

import java.util.List;

import org.app.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CarRepository extends JpaRepository<Car, Long>{
	List<Car> findByMaxSpeedBetween(short minSpeed, short maxSpeed);
}
