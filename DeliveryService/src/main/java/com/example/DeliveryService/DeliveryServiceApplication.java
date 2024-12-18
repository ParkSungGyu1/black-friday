package com.example.DeliveryService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DeliveryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryServiceApplication.class, args);
    }

}
