package com.proshore.powerplant.model.mapper;

import com.proshore.powerplant.model.dao.BatteryDao;
import com.proshore.powerplant.model.dto.BatteryDto;
import com.proshore.powerplant.model.entity.Battery;

import java.util.ArrayList;
import java.util.List;

public class ToDto {
    
    public static BatteryDto toBatteryDtoFromEntity(Battery battery) {
        BatteryDto dto = BatteryDto.builder()
                .name(battery.getName())
                .postcode(battery.getPostcode())
                .capacity(battery.getCapacity())
                .build();
        return dto;
    }

    public static Battery toBatteryEntityFromDao(BatteryDao batteryDao) {
        Battery battery = Battery.builder()
                .id(batteryDao.getName() + batteryDao.getPostcode() + batteryDao.getCapacity())
                .name(batteryDao.getName())
                .postcode(batteryDao.getPostcode())
                .capacity(batteryDao.getCapacity())
                .build();
        return battery;
    }

    public static List<Battery> toBatteryEntityListFromDaoList(List<BatteryDao> batteryDaos) {
        List<Battery> batteries = new ArrayList<>();
        batteryDaos.forEach(batteryDto -> {
            Battery battery = toBatteryEntityFromDao(batteryDto);
            batteries.add(battery);
        });
        return batteries;
    }

    public static BatteryDto toBatteryDtoFromDao(BatteryDao batteryDao) {
        BatteryDto dto = BatteryDto.builder()
                .name(batteryDao.getName())
                .postcode(batteryDao.getPostcode())
                .capacity(batteryDao.getCapacity())
                .build();
        return dto;
    }

    public static List<BatteryDto> toDtoListFromDaoList(List<BatteryDao> batteryDaos) {
        List<BatteryDto> batteries = new ArrayList<>();
        batteryDaos.forEach(batteryDao -> {
            BatteryDto battery = toBatteryDtoFromDao(batteryDao);
            batteries.add(battery);
        });
        return batteries;
    }
    
}
