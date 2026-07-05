package com.microservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="AUTH-SERVICE")
public interface AuthClient {

    @GetMapping("/api/v1/auth/validate")
    void validate(@RequestHeader("Authorization") String token);



}

//DONE:
//
// ✔ Eureka Server
//✔ API Gateway
//✔ Gateway Routing/Security
//✔ CORS
//✔ Auth Service
//   ✔ Register
//   ✔ Login
//   ✔ JWT
//   ✔ Refresh Token
//   ✔ Logout
//   ✔ Validate
//   ✔ Exception
//   ✔ Scheduler
//   ✔ Swagger
//   ✔ Logging
//   ✔ Actuator



