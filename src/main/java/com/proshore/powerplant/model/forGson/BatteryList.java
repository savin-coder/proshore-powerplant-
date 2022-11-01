package com.proshore.powerplant.model.forGson;

import com.proshore.powerplant.model.dao.BatteryDao;
import com.proshore.powerplant.model.entity.Battery;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@RequiredArgsConstructor
public class BatteryList {
    ArrayList<BatteryDao> batteryList;
}
