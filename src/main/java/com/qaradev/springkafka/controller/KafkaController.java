package com.qaradev.springkafka.controller;

import com.qaradev.springkafka.model.Customer;
import com.qaradev.springkafka.service.KafkaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/custom-kafka")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KafkaController {

    final KafkaService kafkaService;

    public KafkaController(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @PostMapping(value = "/{topic}/send", consumes = "application/json")
    public void send(@RequestBody Object object, @PathVariable String topic) throws JsonProcessingException {
        kafkaService.send(object, topic);
    }

    @GetMapping("/getMessage")
    public List<Customer> getCustomers() {
        return kafkaService.getCustomers();
    }

}
