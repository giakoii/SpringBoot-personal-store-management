package org.example.personalstoremanagementproject.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.personalstoremanagementproject.entities.Order;
import org.example.personalstoremanagementproject.entities.Role;
import org.example.personalstoremanagementproject.entities.Status;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;
@Data
@Builder
@FieldDefaults(level = PRIVATE)
public class UserDTO {
        String userName;
        String password;
        String email;
        String fullName;
        String nickName;
        String phone;
        String address;
        Status status;
        Role role;
}
