package org.example.personalstoremanagementproject.repositories;

import org.example.personalstoremanagementproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, String> {
}
