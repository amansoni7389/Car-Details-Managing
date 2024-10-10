package org.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CarSpecs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long carid;
	
	@NotBlank(message = "car name not be empty")
	private String carName;
	
	@Min(value = 0)
	@Max(value = 250)
	private short maxSpeed;
	
	private String engineModel;
	
}
