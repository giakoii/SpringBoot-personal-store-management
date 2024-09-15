package org.example.personalstoremanagementproject.controllers;

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
    //
    @PostMapping("/create")
    User createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    //
    @PostMapping("/login")
    User userLogin(@RequestBody UserDTO userDTO) {
        return userService.userLogin(userDTO.getUserName(), userDTO.getPassword());
    }

    //


    //===================================== GET =====================================
    @GetMapping("/getAllUsers")
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUserById/{userId}")
    User getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    //===================================== PUT =====================================
    @PutMapping("/updateInformationUser/{userId}")
    User updateInformationUser(@PathVariable String userId, @RequestBody UserDTO userDTO) {
        return userService.updateInformationUser(userId, userDTO);
    }

    @PutMapping("/deleteUser/{userId}")
    User deleteUser(@PathVariable String userId) {
        return userService.deleteUser(userId);
    }

    //===================================== DELETE =====================================
}
