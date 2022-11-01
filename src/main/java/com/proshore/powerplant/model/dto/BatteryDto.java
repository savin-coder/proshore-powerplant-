package com.proshore.powerplant.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class BatteryDto {
    private String name;
    private String postcode;
    private Long capacity;
}
