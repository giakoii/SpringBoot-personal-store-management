package project.personal.personalstoremanagementproject.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import project.personal.personalstoremanagementproject.utils.constants.ConstantEnum;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity implements UserDetails{
    //
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

