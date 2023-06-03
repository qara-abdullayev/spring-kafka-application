package com.qaradev.springkafka.service;

import com.qaradev.springkafka.config.properties.ApplicationProperties;
import com.qaradev.springkafka.model.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KafkaService {

    final KafkaTemplate<String, String> kafkaTemplate;
    final ObjectMapper objectMapper;

    final List<Customer> customers = new ArrayList<>();


    public void send(Object object, String topic) throws JsonProcessingException {
        kafkaTemplate.send(topic, objectMapper.writeValueAsString(object));
    }

    @KafkaListener(topics = ApplicationProperties.KAFKA_TOPIC)
    public void listen(Customer customer) {
        if (customer.getAge() > 22) {
            customers.add(customer);
        }
    }

    public List<Customer> getCustomers() {
        return customers;
    }

}
