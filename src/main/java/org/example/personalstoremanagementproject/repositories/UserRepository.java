package org.example.personalstoremanagementproject.repositories;

import org.example.personalstoremanagementproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, String> {
    User findByEmail(String email);
    User findByUserNameAndPassword(String userName, String password);
    User findByUserName(String userName);
}
