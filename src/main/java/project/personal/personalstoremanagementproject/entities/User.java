package project.personal.personalstoremanagementproject.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import project.personal.personalstoremanagementproject.utils.constants.ConstantEnum;

import java.time.LocalDateTime;

@Entity
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity{
    @Id
    @Column(name = "USER_ID", nullable = false, length = 100)
    String userId;

    @Column(name = "USER_NAME", nullable = false, unique = true, length = 255)
    String userName;

    @Column(name = "PASSWORD",nullable = false)
    String password;

    @Column(name = "FULL_NAME")
    String fullName;

    @Column(name = "NICK_NAME")
    String nickName;

    @Column(name = "PHONE_NUMBER")
    String phoneNumber;

    @Column(name = "EMAIL",unique = true)
    String email;

    @Column(name = "ADDRESS")
    String address;

    @Column(name = "ROLE_ID")
    @Enumerated(EnumType.STRING)
    ConstantEnum.Role role;

    @Column(name = "LAST_LOGIN")
    LocalDateTime lastLogin;
}

