package org.example.personalstoremanagementproject.repositories;

import org.example.personalstoremanagementproject.entities.Status;
import org.example.personalstoremanagementproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, String> {
    User findByEmail(String email);
    User findByUserNameAndPassword(String userName, String password);
    Optional<User> findByUserName(String userName);
    User findByStatus(Status status);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
}
