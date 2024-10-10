package org.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBoot005Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot005Application.class, args);
	}

//	@Autowired
//	private CarRepository carRep;
//	
//	@Override
//	public void run(String... args) throws Exception {
//		Car car = Car.builder().carName("Sumo").engineModel("2000cc").maxSpeed((short) 160).build();
//		
//		carRep.save(car);
//	}


}
