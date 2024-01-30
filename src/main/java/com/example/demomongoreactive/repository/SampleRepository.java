package com.example.demomongoreactive.repository;

import com.example.demomongoreactive.entity.SampleEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleRepository extends ReactiveMongoRepository<SampleEntity, String> {
}
