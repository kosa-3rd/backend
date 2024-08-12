package com.example.demo.interfaces.controller.users;

public class UserDto {

    public record DuplicatedEmailCheckRequest(String email) {
        public void validate() {

        }
    }

    public record SignUpRequest(String email,String username ,String password, String nickname) {
        public void validate() {
            if (email == null || email.isBlank()) {
                throw new IllegalArgumentException("email is blank");
            }
            if(username == null || username.isBlank()){
                throw new IllegalArgumentException("username is blank");
            }
            if (password == null || password.isBlank()) {
                throw new IllegalArgumentException("password is blank");
            }
            if (nickname == null || nickname.isBlank()) {
                throw new IllegalArgumentException("nickname is blank");
            }
        }
    }


    public record LoginRequest(String email, String password) {
        public void validate() {
            if (email == null || email.isBlank()) {
                throw new IllegalArgumentException("email is blank");
            }
            if (password == null || password.isBlank()) {
                throw new IllegalArgumentException("password is blank");
            }
        }
    }

    public record SignUpResponse(String email, String token) {

    }

    public record LoginResponse(String email, String token) {
    }

    public record DeleteRequest(String email) {
        public void validate() {
            if (email == null || email.isBlank()) {
                throw new IllegalArgumentException("email is blank");
            }
        }
    }

    public record DeleteResponse (String message) {
    }

    public record ModifyRequest(String username, String password) {
        public void validate() {
            if(username == null || username.isBlank()){
                throw new IllegalArgumentException("username is blank");
            }
            if (password == null || password.isBlank()) {
                throw new IllegalArgumentException("password is blank");
            }
        }
    }

    public record ModifyResponse (String username) {
    }
}
