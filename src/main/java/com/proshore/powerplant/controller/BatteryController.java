package com.proshore.powerplant.controller;

import com.proshore.powerplant.model.dao.BatteryDao;
import com.proshore.powerplant.model.dto.BatteryDto;
import com.proshore.powerplant.model.dto.RangeDto;
import com.proshore.powerplant.model.entity.Battery;
import com.proshore.powerplant.service.BatteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/batteries")
public class BatteryController {

    @Autowired
    private BatteryService batteryService;

    @PostMapping()
    public List<BatteryDto> saveBattery(@RequestBody List<BatteryDao> batteries) {
         return batteryService.saveBatteries(batteries);
    }

    @GetMapping("/{postcodeFrom}/{postcodeTo}")
    public RangeDto getRangeInfo(@PathVariable String postcodeFrom, @PathVariable String postcodeTo) {
        long from, to;
        if (Long.parseLong(postcodeFrom) <= Long.parseLong(postcodeTo)) {
            from = Long.parseLong(postcodeFrom);
            to = Long.parseLong(postcodeTo);
        } else {
            from = Long.parseLong(postcodeTo);
            to = Long.parseLong(postcodeFrom);
        }
        return batteryService.getRangeInfo(from, to);
    }

}
