package com.microservice.controller;

import com.microservice.dto.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @GetMapping("/{id}")
    public UserResponse getUser(
            @PathVariable Long id
    ){

        return new UserResponse();//userService.getUser(id);

    }
}
