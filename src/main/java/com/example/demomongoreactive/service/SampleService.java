package com.example.demomongoreactive.service;

import com.example.demomongoreactive.entity.SampleEntity;
import com.example.demomongoreactive.model.SampleModel;
import com.example.demomongoreactive.repository.SampleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Random;

@Service
public class SampleService {

    final SampleRepository repository;

    Logger logger = LoggerFactory.getLogger(getClass());

    public SampleService(SampleRepository repository) {
        this.repository = repository;
    }

    public Mono<SampleModel> testInput(SampleModel model) {
        Random rng = new Random();
        if (model == null) {
            model = new SampleModel();
            model.setMessage("");
        }

        Long rngResult = rng.nextLong();
        model.setMessage(model.getMessage().concat(String.valueOf(rngResult)));
        SampleEntity entity = new SampleEntity();
        BeanUtils.copyProperties(model, entity);
        logger.info("saving {}", entity);
        return repository.save(entity).map(result -> {
            SampleModel finalModel = new SampleModel();
            BeanUtils.copyProperties(result, finalModel);
            return finalModel;
        });
    }

    public Mono<SampleModel> testGet(String id) {
        Mono<SampleEntity> entityMono = repository.findById(id);
        return entityMono.map(result -> {
            SampleModel finalModel = new SampleModel();
            BeanUtils.copyProperties(result, finalModel);
            return finalModel;
        });
    }
}
