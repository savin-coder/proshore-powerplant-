package com.proshore.powerplant.repository;

import com.proshore.powerplant.model.dao.BatteryDao;
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

@Testcontainers
@SpringBootTest
class BatteryRepositoryTest {

    @Autowired
    private BatteryRepository batteryRepository;

    @Container
    public static MongoDBContainer container = new MongoDBContainer(DockerImageName.parse("mongo:latest"));

    BatteryRepositoryTest() throws IOException {
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

    private final ObjectMapper mapper = new ObjectMapper();
    private final BatteryList batteryList = mapper.readValue(TestData.testJsonData, BatteryList.class);
    private final ArrayList<BatteryDao> testData = batteryList.getBatteryList();
    private final List<Battery> testEntities = ToDto.toBatteryEntityListFromDaoList(testData);

    @Test
    void saveAll() {
        Assertions.assertEquals(batteryRepository.saveAll(testEntities), testEntities);
    }

    @Test
    void findAll() {
        batteryRepository.saveAll(testEntities);
        List<Battery> findAllResult = batteryRepository.findAll();
        Assertions.assertEquals(findAllResult.size(), testEntities.size());
        Assertions.assertEquals(findAllResult.toString() , testEntities.toString());


        for (int index = 0; index < findAllResult.size(); index++) {
            Assertions.assertEquals(findAllResult.get(index).toString() , testEntities.get(index).toString());
        }
    }
}