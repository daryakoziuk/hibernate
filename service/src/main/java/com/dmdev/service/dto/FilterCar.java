package com.dmdev.service.dto;

import com.dmdev.service.entity.TypeFuel;
import com.dmdev.service.entity.TypeTransmission;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class FilterCar {

    private String model;
    private Integer engineVolume;
    private TypeTransmission transmission;
    private TypeFuel type;
    private LocalDate dateRelease;
}
