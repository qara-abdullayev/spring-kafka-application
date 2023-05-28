package com.qaradev.springkafka.model;

import lombok.Data;

@Data
public class CreditEvent {

    String processId;
    String event;
    String value;

}
