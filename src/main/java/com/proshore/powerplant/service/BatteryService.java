package com.proshore.powerplant.service;

import com.proshore.powerplant.model.dao.BatteryDao;
import com.proshore.powerplant.model.dto.BatteryDto;
import com.proshore.powerplant.model.dto.RangeDto;
import com.proshore.powerplant.model.entity.Battery;

import java.util.List;

public interface BatteryService {
    List<BatteryDto> saveBatteries(List<BatteryDao> batteries);

    RangeDto getRangeInfo(long postcodeFrom, long postcodeTo);
}
