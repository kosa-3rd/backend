package com.example.demo.interfaces.controller.users;

import com.example.demo.application.UserProcessor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users")
@RestController
public class UserController {
    private final UserProcessor userProcessor;

    public UserController(UserProcessor userProcessor) {
        this.userProcessor = userProcessor;
    }

    @PostMapping("/signup")
    public UserDto.SignUpResponse signup(@RequestBody UserDto.SignUpRequest request) {
        request.validate();
        return UserMapper.toSignUpResponse(
                        userProcessor.signup(
                                UserMapper.toSignUp(request)
                        )
                );
    }

    @PostMapping("/login")
    public UserDto.LoginResponse login(@RequestBody UserDto.LoginRequest request) {
        request.validate();
        return UserMapper.toLoginResponse(
                userProcessor.login(
                        UserMapper.toLogin(request)
                )
        );
    }
    @PatchMapping
    public UserDto.ModifyResponse modifyUser(@RequestHeader("Authorization") String token,
            @RequestBody UserDto.ModifyRequest request) {
        request.validate();
        return UserMapper.toModifyResponse(
                userProcessor.modifyUser(
                        UserMapper.toModify(request,token)
                )
        );
    }

    @DeleteMapping
    public UserDto.DeleteResponse deleteUser(@RequestHeader("Authorization") String token,
            @RequestBody UserDto.DeleteRequest request) {
        request.validate();
        return UserMapper.toDeleteResponse(
                userProcessor.deleteUser(
                        UserMapper.toDelete(request,token)
                )
        );

    }


}
