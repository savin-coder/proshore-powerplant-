package com.proshore.powerplant.model.dao;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatteryDao {
    private String name;
    private String postcode;
    private Long capacity;
}
