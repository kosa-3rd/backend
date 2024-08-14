package com.example.demo.domain.users;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserCommand {
    public record SignUp(String email,String username, String password, String nickname) { }
    public record Login(String email, String password) { }
    public record Delete(String email, String token) {
    }

    public record Modify(String token, String username, String password) {
    }

    public record ModifyPassword(String password, String token) {
    }

    public record NickNameModify(String nickname, String token) {
    }
}
