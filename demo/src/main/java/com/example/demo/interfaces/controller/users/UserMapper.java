package com.example.demo.interfaces.controller.users;

import com.example.demo.application.UserProcessor;
import com.example.demo.domain.users.User;
import com.example.demo.domain.users.UserCommand;

public class UserMapper {
    public static UserDto.SignUpResponse toSignUpResponse(UserProcessor.SignUpInfo signUpInfo) {
        return new UserDto.SignUpResponse(signUpInfo.email(), signUpInfo.token());
    }

    public static UserCommand.SignUp toSignUp(UserDto.SignUpRequest request) {
        return new UserCommand.SignUp(request.email(), request.username(), request.password());

    }

    public static UserDto.LoginResponse toLoginResponse(UserProcessor.UserInfo login) {
        return new UserDto.LoginResponse(login.email(), login.token());
    }

    public static UserCommand.Login toLogin(UserDto.LoginRequest request) {
        return new UserCommand.Login(request.email(), request.password());
    }

    public static UserCommand.Delete toDelete(UserDto.DeleteRequest request, String token) {
        return new UserCommand.Delete(request.email(), token);
    }

    public static UserDto.DeleteResponse toDeleteResponse(User user) {
        return new UserDto.DeleteResponse(user.getUserEmail());
    }

    public static UserCommand.Modify toModify(UserDto.ModifyRequest request, String token) {
        return new UserCommand.Modify(token, request.username(), request.password());
    }

    public static UserDto.ModifyResponse toModifyResponse(User user) {
        return new UserDto.ModifyResponse(user.getUsername());
    }
}
