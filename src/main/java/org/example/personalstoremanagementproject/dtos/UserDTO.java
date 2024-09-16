package org.example.personalstoremanagementproject.dtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
        @NotBlank
        String userName;

        @NotBlank
        @Size(min = 8, message = "Password must be at least 8 characters")
        String password;

        String email;

        @NotBlank
        String fullName;

        @NotBlank
        String nickName;

        @NotBlank
        String phone;

        @NotBlank
        String address;

        Status status;
        Role role;
}
