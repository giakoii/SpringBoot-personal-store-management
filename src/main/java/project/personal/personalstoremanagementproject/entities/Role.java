package project.personal.personalstoremanagementproject.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "Roles")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    Integer roleId;

    @Column(name = "USER_ID",nullable = false, unique = true, length = 100)
    String userId;

    @Column(name = "ROLE_NAME",nullable = false, unique = true, length = 100)
    String roleName;

    @Column(name = "DESCRIPTION")
    String description;
}