package org.example.personalstoremanagementproject.services;

import org.example.personalstoremanagementproject.dtos.UserDTO;
import org.example.personalstoremanagementproject.entities.User;
import org.example.personalstoremanagementproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class UserServiceImp implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserDTO userDTO) {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        long count = userRepository.count();
        String formattedCount = String.format("%03d", count + 1);
        String userId = "U" + date + formattedCount;

        try {
            User user = User.builder()
                    .userId(userId)
                    .userName(userDTO.getUserName())
                    .password(userDTO.getPassword())
                    .email(userDTO.getEmail())
                    .fullName(userDTO.getFullName())
                    .nickName(userDTO.getNickName())
                    .phone(userDTO.getPhone())
                    .address(userDTO.getAddress())
                    .role(userDTO.getRole())
                    .build();
            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public User updateUser(String userId, UserDTO userDTO) {
        return null;
    }
}
