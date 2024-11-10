package project.personal.personalstoremanagementproject.utils;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import project.personal.personalstoremanagementproject.entities.User;

@Component
public class FunctionUtil {
    /**
     * Get current username
     * @return
     */
    public static User getCurrentUser() {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            return (User) principal;
        } else {
            throw new IllegalStateException("Principal is not an instance of User");
        }
    }

}