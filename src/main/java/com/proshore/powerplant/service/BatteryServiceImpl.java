package com.proshore.powerplant.service;

import com.proshore.powerplant.model.dao.BatteryDao;
import com.proshore.powerplant.model.dto.BatteryDto;
import com.proshore.powerplant.model.dto.RangeDto;
import com.proshore.powerplant.model.entity.Battery;
import com.proshore.powerplant.model.mapper.ToDto;
import com.proshore.powerplant.repository.BatteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BatteryServiceImpl implements BatteryService{

    @Autowired
    private BatteryRepository batteryRepository;

    @Override
    public List<BatteryDto> saveBatteries(List<BatteryDao> batteryDaos) {
        List<BatteryDto> dtoList = new ArrayList<>();
        List<Battery> batteryEntities = new ArrayList<>();

        batteryDaos.forEach(batteryDao -> {
            Battery batteryEntity = ToDto.toBatteryEntityFromDao(batteryDao);
            batteryEntities.add(batteryEntity);
        });

        List<Battery> savedBatteries = batteryRepository.saveAll(batteryEntities);
        savedBatteries.forEach(battery -> {
            BatteryDto dto = ToDto.toBatteryDtoFromEntity(battery);
            dtoList.add(dto);
        });
        return dtoList;
    }

    @Override
    public RangeDto getRangeInfo(long postcodeFrom, long postcodeTo) {

        List<Battery> allBattery = batteryRepository.findAll();
        List<Battery> batteriesWithPostCodeRange = allBattery.stream()
                .filter(battery -> (Long.parseLong(battery.getPostcode()) <= postcodeTo ) && (Long.parseLong(battery.getPostcode()) >= postcodeFrom ) )
                .toList();

        List<String> batteryNames = new ArrayList<>();
        AtomicLong total = new AtomicLong(0L);
        batteriesWithPostCodeRange.forEach(battery -> {
            batteryNames.add(battery.getName());
            total.addAndGet(battery.getCapacity());
        });
        double average = 0.00D;
        int totalNumber = batteriesWithPostCodeRange.size();
        if(totalNumber != 0) {
             average = (double) total.get()/totalNumber;
        }

        Collections.sort(batteryNames);

        return RangeDto.builder()
                .batteryNames(batteryNames)
                .totalNumber(totalNumber)
                .averageCapacity(average)
                .totalCapacity(total.get())
                .build();
    }

}
