package project.personal.personalstoremanagementproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.personal.personalstoremanagementproject.entities.EmailTemplate;

import java.util.Optional;

@Repository
public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Integer> {
    Optional<EmailTemplate> findByScreenName(String screenName);
}