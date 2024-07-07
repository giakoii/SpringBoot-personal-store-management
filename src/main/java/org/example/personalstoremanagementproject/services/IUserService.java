package org.example.personalstoremanagementproject.services;

import org.example.personalstoremanagementproject.dtos.UserDTO;
import org.example.personalstoremanagementproject.entities.User;

public interface IUserService {
    User createUser(UserDTO userDTO);
    User updateUser(String userId,UserDTO userDTO);
}
