package project.personal.personalstoremanagementproject.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import project.personal.personalstoremanagementproject.entities.User;
import project.personal.personalstoremanagementproject.repositories.UserRepository;

@Component
public class FunctionUtil {

    @Autowired
    private UserRepository userRepository;

    /**
     * Get current username
     * @return
     */
    public User getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
                // Lấy thông tin từ authentication.getPrincipal()
                org.springframework.security.core.userdetails.User springUser =
                        (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

                // Lấy thông tin chi tiết từ repository
                return userRepository.findByUserNameAndIsActiveTrue(springUser.getUsername())
                        .orElseThrow(() -> new RuntimeException("User not found"));
            }
        }
        return null;
    }


}