package com.example.demo.infra.users;

import com.example.demo.domain.users.User;
import com.example.demo.domain.users.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRepositoryImplTest {
    @Autowired
    private UserJPARepository userJPARepository;

    @Autowired
    private UserRepositoryImpl userRepository;

    private final String userEmail = "sample@sample.com";

    @Autowired
    private UserService userService;

    @Test
    void test(){
        Optional<User> userByEmail = userRepository.getUserByEmail(userEmail);
        User user = userByEmail.orElseThrow(() -> new RuntimeException("wow"));
        System.out.println(user.getUsername());
        System.out.println(user.getUserEmail());
        User user1 = userService.validateUser(userEmail);
        System.out.println(user1.getUserEmail());
        System.out.println(user1.getUsername());

    }

}