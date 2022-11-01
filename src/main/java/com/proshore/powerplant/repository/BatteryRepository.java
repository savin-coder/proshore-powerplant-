package com.proshore.powerplant.repository;

import com.proshore.powerplant.model.entity.Battery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@EnableMongoRepositories
public interface BatteryRepository extends MongoRepository<Battery, String>{
    @Override
    <S extends Battery> List<S> saveAll(Iterable<S> entities);

    @Override
    List<Battery> findAll();
}
