package org.example.personalstoremanagementproject.controllers;

import org.example.personalstoremanagementproject.dtos.UserDTO;
import org.example.personalstoremanagementproject.entities.User;
import org.example.personalstoremanagementproject.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello User!";
    }

    @PostMapping("/create")
    public User createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }
}
