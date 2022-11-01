package com.proshore.powerplant.model.mapper;

import com.proshore.powerplant.model.dao.BatteryDao;
import com.proshore.powerplant.model.dto.BatteryDto;
import com.proshore.powerplant.model.entity.Battery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToDtoTest {

    BatteryDao dao = new BatteryDao("Heavy Power", "1234", 12345L);
    Battery battery = new Battery("Heavy PowerX123412345","Heavy PowerX", "5678", 67890L);
    BatteryDto dto = new BatteryDto("Heavy Power Danger", "9012", 12212L);


    @Test
    void toBatteryDtoFromEntity() {
        BatteryDto dto = ToDto.toBatteryDtoFromEntity(battery);
        Assertions.assertEquals(battery.getName(), dto.getName());
        Assertions.assertEquals(battery.getCapacity(), dto.getCapacity());
        Assertions.assertEquals(battery.getPostcode(), dto.getPostcode());
    }

    @Test
    void toBatteryEntityListFromDaoList() {
        List<BatteryDao> batteryDaos = new ArrayList<>();
        batteryDaos.add(dao);
        List<Battery> batteryList = ToDto.toBatteryEntityListFromDaoList(batteryDaos);

        Assertions.assertEquals(batteryList.get(0).getId(), dao.getName()+dao.getPostcode()+dao.getCapacity());
        Assertions.assertEquals(batteryList.get(0).getName(), dao.getName());
        Assertions.assertEquals(batteryList.get(0).getPostcode(), dao.getPostcode());
        Assertions.assertEquals(batteryList.get(0).getCapacity(), dao.getCapacity());
    }

    @Test
    void toBatteryDtoFromDao() {
        BatteryDto dto1 = ToDto.toBatteryDtoFromDao(dao);
        Assertions.assertEquals(dto1.getName(), dao.getName());
        Assertions.assertEquals(dto1.getPostcode(), dao.getPostcode());
        Assertions.assertEquals(dto1.getCapacity(), dao.getCapacity());
    }

    @Test
    void toDtoListFromDaoList() {
        List<BatteryDao> daoList = new ArrayList<>();
        daoList.add(dao);

        List<BatteryDto> dtoList = ToDto.toDtoListFromDaoList(daoList);

        Assertions.assertEquals(dtoList.get(0).getName(), dao.getName());
        Assertions.assertEquals(dtoList.get(0).getPostcode(), dao.getPostcode());
        Assertions.assertEquals(dtoList.get(0).getCapacity(), dao.getCapacity());

    }

    @Test
    void toBatteryEntityFromDao() {
        Battery battery1 = ToDto.toBatteryEntityFromDao(dao);
        Assertions.assertEquals(battery1.getId(), dao.getName()+dao.getPostcode()+dao.getCapacity());
        Assertions.assertEquals(battery1.getName(), dao.getName());
        Assertions.assertEquals(battery1.getPostcode(), dao.getPostcode());
        Assertions.assertEquals(battery1.getCapacity(), dao.getCapacity());
    }
}