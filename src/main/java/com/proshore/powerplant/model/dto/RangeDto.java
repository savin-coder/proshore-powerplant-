package com.proshore.powerplant.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class RangeDto {
    private List<String> batteryNames;
    private int totalNumber;
    private long totalCapacity;
    private double averageCapacity;
}
