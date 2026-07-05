package com.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}




//                Git Config Repo
//                       │
//                       ▼
//             Config Server (8888)
//                       │
//        ┌──────────────┼──────────────┐
//        ▼              ▼              ▼
//  Service Registry   API Gateway   Auth Service
//        │              │
//        ├──────────────┼────────────────────┐
//        ▼              ▼                    ▼
//  User Service    Order Service    Notification Service
//                       │
//                       ▼
//                   PostgreSQL