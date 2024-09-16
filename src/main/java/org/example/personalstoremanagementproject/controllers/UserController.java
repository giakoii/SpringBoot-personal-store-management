package org.example.personalstoremanagementproject.controllers;

import jakarta.validation.Valid;
import org.example.personalstoremanagementproject.dtos.ApiResponse;
import org.example.personalstoremanagementproject.dtos.UserDTO;
import org.example.personalstoremanagementproject.entities.User;
import org.example.personalstoremanagementproject.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;


    //===================================== POST =====================================
    //Method to create a new user
    @PostMapping("/create")
    ApiResponse<User> createUser(@RequestBody @Valid UserDTO userDTO) {
        ApiResponse<User> response = new ApiResponse<>();
        response.setStatus(200);
        response.setCode(200);
        response.setResult(userService.createUser(userDTO));
        return response;
    }

    //Method to login
    @PostMapping("/login")
    ApiResponse<User> userLogin(@RequestBody UserDTO userDTO) {
        ApiResponse<User> response = new ApiResponse<>();
        response.setStatus(200);
        response.setCode(200);
        response.setResult(userService.userLogin(userDTO.getUserName(), userDTO.getPassword()));
        return response;
    }
    //Method forgot password

    //===================================== GET =====================================
    //Method to get all users
    @GetMapping("/getAllUsers")
    public ApiResponse<List<User>> getAllUsers() {
        ApiResponse<List<User>> response = new ApiResponse<>();
        response.setStatus(200);
        response.setCode(200);
        response.setResult(userService.getAllUsers());
        return response;
    }

    //Method to get user by userId
    @GetMapping("/getUserById/{userId}")
    ApiResponse<User> getUserById(@PathVariable String userId) {
        ApiResponse<User> response = new ApiResponse<>();
        response.setStatus(200);
        response.setCode(200);
        response.setResult(userService.getUserById(userId));
        return response;
    }

    //===================================== PUT =====================================
    @PutMapping("/updateInformationUser/{userId}")
    ApiResponse<User> updateInformationUser(@PathVariable String userId, @RequestBody UserDTO userDTO) {
        ApiResponse<User> response = new ApiResponse<>();
        response.setStatus(200);
        response.setCode(200);
        response.setResult(userService.updateInformationUser(userId, userDTO));
        return response;
    }

    @PutMapping("/deleteUser/{userId}")
    ApiResponse<User> deleteUser(@PathVariable String userId) {
        ApiResponse<User> response = new ApiResponse<>();
        response.setStatus(200);
        response.setCode(200);
        response.setResult(userService.deleteUser(userId));
        return response;
    }
    //===================================== DELETE =====================================
}
