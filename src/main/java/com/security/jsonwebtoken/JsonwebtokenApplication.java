package com.security.jsonwebtoken;

import com.security.jsonwebtoken.user.User;
import com.security.jsonwebtoken.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JsonwebtokenApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public JsonwebtokenApplication(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(JsonwebtokenApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        addUser("admin", "admin");
        addUser("user", "user");
    }

    public void addUser(String username, String password) {
        if (userRepository.findByUsername(username).isEmpty()) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
        }
    }
}
