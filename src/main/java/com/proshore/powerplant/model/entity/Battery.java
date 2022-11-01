package com.proshore.powerplant.model.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Document(collection = "battery")
public class Battery {
    @MongoId
    private String id;
    private String name;
    private String postcode;
    private Long capacity;

}
