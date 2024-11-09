package project.personal.personalstoremanagementproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.personal.personalstoremanagementproject.repositories.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private  UserRepository userRepository;

    /**
     * Load user by username
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserNameAndIsActiveTrue(username).orElseThrow();
    }
}
