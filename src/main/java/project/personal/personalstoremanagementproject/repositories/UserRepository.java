package project.personal.personalstoremanagementproject.repositories;

import project.personal.personalstoremanagementproject.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, String> {
    User findByEmail(String email);
    boolean existsByEmailAndUserName(String email, String userName);
    User findByUserNameAndPassword(String userName, String password);
    Optional<User> findByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
}
