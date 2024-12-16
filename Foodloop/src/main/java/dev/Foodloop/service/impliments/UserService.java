package dev.Foodloop.service.impliments;

import dev.Foodloop.persistance.dao.UserRepository;
import dev.Foodloop.persistance.entities.User;
import dev.Foodloop.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // Authenticate User (Login)
    public boolean authenticateUser(String email, String rawPassword) {
        User existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("email not found"));

        // Compare raw password with encoded password
        return passwordEncoder.matches(rawPassword, existingUser.getPassword());
    }
}