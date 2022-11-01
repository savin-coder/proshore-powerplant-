package com.proshore.powerplant.controller;

import com.proshore.powerplant.model.dao.BatteryDao;
import com.proshore.powerplant.model.dto.BatteryDto;
import com.proshore.powerplant.model.dto.RangeDto;
import com.proshore.powerplant.model.entity.Battery;
import com.proshore.powerplant.model.forGson.BatteryList;
import com.proshore.powerplant.model.mapper.ToDto;
import com.proshore.powerplant.testdata.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Testcontainers
class BatteryControllerTest {

    @Container
    public static MongoDBContainer container = new MongoDBContainer(DockerImageName.parse("mongo:latest"));

    @Autowired
    private BatteryController batteryController;

    BatteryControllerTest() throws IOException {
    }

    @BeforeAll
    static void initAll() {
        container.start();
    }

    @Test
    void containersStartedAndPublicPortIsAvailable() {
        assertThatPortIsAvailable(container);
    }

    private void assertThatPortIsAvailable(MongoDBContainer container) {
        try {
            new Socket(container.getHost(), container.getFirstMappedPort());
        } catch (IOException e) {
            throw new AssertionError("The expected port " + container.getFirstMappedPort() + " is not available");
        }
    }

    ObjectMapper mapper = new ObjectMapper();
    BatteryList batteryList = mapper.readValue(TestData.testJsonData, BatteryList.class);
    List<BatteryDao> testData = batteryList.getBatteryList();

    @Test
    void saveBattery() {
        List<Battery> testEntities = ToDto.toBatteryEntityListFromDaoList(testData);
        batteryController.saveBattery(testData);

        List<BatteryDto> actualDtoList = new ArrayList<>();
        testEntities.forEach(entity -> {
            BatteryDto batteryDto = ToDto.toBatteryDtoFromEntity(entity);
            actualDtoList.add(batteryDto);
        });

        List<BatteryDto> returnedDtoList = batteryController.saveBattery(testData);

        Assertions.assertEquals(returnedDtoList.size(), actualDtoList.size());

        Assertions.assertEquals(returnedDtoList.toString(), actualDtoList.toString());

        for (int index = 0; index < returnedDtoList.size(); index++) {
            Assertions.assertEquals(returnedDtoList.get(index).toString(), actualDtoList.get(index).toString());
        }
    }


    @Test
    void getRangeInfo() {
        batteryController.saveBattery(testData);

        RangeDto rangeDto1 = batteryController.getRangeInfo("8000", "10000");

        Assertions.assertEquals(rangeDto1.getTotalCapacity(), 63500);
        Assertions.assertEquals(rangeDto1.getAverageCapacity(), 31750.0D);
        Assertions.assertEquals(rangeDto1.getTotalNumber(), 2);
        Assertions.assertEquals(rangeDto1.getBatteryNames().get(0), "Gold Coast Mc");

        RangeDto rangeDto2 = batteryController.getRangeInfo("0", "10000");

        Assertions.assertEquals(rangeDto2.getTotalCapacity(), 539000);
        Assertions.assertEquals(rangeDto2.getAverageCapacity(), 26950.0D);
        Assertions.assertEquals(rangeDto2.getTotalNumber(), 20);
        Assertions.assertEquals(rangeDto2.getBatteryNames().get(5), "Carmel");
    }
}