package org.example.personalstoremanagementproject.services;

import org.example.personalstoremanagementproject.dtos.UserDTO;
import org.example.personalstoremanagementproject.entities.User;

import java.util.List;

public interface IUserService {
    User createUser(UserDTO userDTO);
    User userLogin(String userName, String password);
    List<User> getAllUsers();
    User getUserById(String userId);
    User getUserByUserName(String userName);
    User updateInformationUser(String userId, UserDTO userDTO);
    User deleteUser(String userId);
}
