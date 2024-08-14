package com.example.demo.application;

import com.example.demo.domain.users.User;
import com.example.demo.domain.users.UserCommand;
import com.example.demo.domain.users.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserProcessor {
    private final UserService userService;
    private final TokenManager tokenManager;

    public UserProcessor(UserService userService, TokenManager tokenManager) {
        this.userService = userService;
        this.tokenManager = tokenManager;
    }

    public SignUpInfo signup(UserCommand.SignUp command) {
        userService.checkUser(command.email());
        User user = userService.saveUser(new User(command.email(), command.username(), command.password(), command.nickname()));
        return new SignUpInfo(user.getUsername(), tokenManager.generateToken(user.getUserEmail()));
    }
    public UserInfo login(UserCommand.Login command) {
        User login = userService.login(command.email(), command.password());
        return new UserInfo(login.getUserEmail(), login.getNickname(), tokenManager.generateToken(login.getUserEmail()));
    }

    public User deleteUser(UserCommand.Delete delete) {
        tokenManager.validateToken( delete.token());
        return userService.deleteUser(delete.email());
    }

    public User modifyUser(UserCommand.Modify modify) {
        String userEmail = tokenManager.validateToken(modify.token());
        User user = userService.getUserByEmail(userEmail);
        user.setUsername(modify.username());
        user.setPassword(modify.password());
        return userService.saveUser(user);
    }

    public Boolean validateUser(String userEmail) {
        return userService.validateCheck(userEmail);
    }

    public User modifyPassword(UserCommand.ModifyPassword modifyPassword) {
        String userEmail = tokenManager.validateToken(modifyPassword.token());
        User user = userService.getUserByPassword(userEmail);
        user.setPassword(modifyPassword.password());
        return userService.saveUser(user);
    }

    public Boolean validatePassword(String userEmail) {
        User userByPassword = userService.getUserByPassword(userEmail);
        return userByPassword != null;
    }

    public record SignUpInfo(String email, String token) {
    }
    public record UserInfo(String email, String nickname, String token) {
    }
}
