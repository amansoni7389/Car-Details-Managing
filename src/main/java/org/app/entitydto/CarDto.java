package org.app.entitydto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDto	 {
    
    private Long carid;

    @NotBlank(message = "car name not be empty")
    private String carName;

    @Min(value = 0)
    @Max(value = 250)
    private short maxSpeed;

    private String engineModel;
}
