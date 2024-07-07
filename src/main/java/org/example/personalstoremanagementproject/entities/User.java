package org.example.personalstoremanagementproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "tbl_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class User {
    @Id
    @Column(name = "user_id", nullable = false, length = 50)
    String userId;

    @Column(name = "user_name", nullable = false, length = 50)
    String userName;

    @Column(name = "password", nullable = false, length = 50)
    String password;

    @Column(name = "email", nullable = false, length = 50)
    String email;

    @Column(name = "full_name", nullable = false, length = 50)
    String fullName;

    @Column(name = "nick_name", nullable = false, length = 50)
    String nickName;

    @Column(name = "phone", nullable = false, length = 50)
    String phone;

    @Column(name = "address", nullable = false, length = 50)
    String address;

    @Column(name = "role", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Order> orders;

}
