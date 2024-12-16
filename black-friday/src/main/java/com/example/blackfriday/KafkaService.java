package com.example.blackfriday;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void publish(){
        kafkaTemplate.send("topic1", "message sent(topic1)");
    }

    @KafkaListener(topics = "topic1", groupId = "testgroup")
    public void consume(String message){
        System.out.println("consumed " + message);
    }
}
