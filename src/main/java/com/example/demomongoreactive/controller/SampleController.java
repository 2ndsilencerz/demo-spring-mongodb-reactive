package com.example.demomongoreactive.controller;

import com.example.demomongoreactive.model.SampleModel;
import com.example.demomongoreactive.service.SampleService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController("/test")
public class SampleController {

    final SampleService service;

    public SampleController(SampleService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/input/get")
    public Mono<SampleModel> testInputByGet() {
        return service.testInput(null);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/input/post")
    public Mono<SampleModel> testInputByPost(@RequestBody SampleModel model) {
        return service.testInput(model);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public Mono<SampleModel> testGetData(@RequestParam String id) {
        return service.testGet(id);
    }
}
