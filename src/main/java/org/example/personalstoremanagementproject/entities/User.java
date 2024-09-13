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
@Table(name = "tbl_User")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class User {
    @Id
    @Column(name = "user_id", length = 10)
    String userId;

    @Column(name = "user_name", length = 255)
    String userName;

    @Column(name = "password", length = 255)
    String password;

    @Column(name = "email", length = 255)
    String email;

    @Column(name = "full_name", columnDefinition = "NVARCHAR(MAX)")
    String fullName;

    @Column(name = "nick_name", columnDefinition = "NVARCHAR(MAX)")
    String nickName;

    @Column(name = "phone", length = 15)
    String phone;

    @Column(name = "address", columnDefinition = "NVARCHAR(MAX)")
    String address;

    @Column(name = "role", length = 50)
    @Enumerated(EnumType.STRING)
    Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Order> orders;

}
