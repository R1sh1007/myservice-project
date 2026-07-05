package com.microservice.service;



import com.microservice.client.UserClient;
import com.microservice.dto.UserDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;



@Service
@RequiredArgsConstructor
public class OrderService {


    private final UserClient userClient;



    @CircuitBreaker(
            name = "userService",
            fallbackMethod = "userFallback"
    )

    public UserDto getUserDetails(
            Long userId
    ){


        return userClient.getUser(userId);


    }




    public UserDto userFallback(

            Long userId,

            Throwable ex

    ){


        return new UserDto();


    }



}
