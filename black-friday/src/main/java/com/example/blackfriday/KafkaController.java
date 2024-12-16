package com.example.blackfriday;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KafkaController {
    private final KafkaService kafkaService;

    @GetMapping("/kafka-test")
    public void kafkaTest(){
        kafkaService.publish();
    }
}
