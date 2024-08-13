package com.example.demo.domain.posts;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PostCommand {

    public record UserPosts (String token) {
    }

    public record UpdatePost(String token, String title, String content, Long postId) {
    }

    public record CreatePost(String token, String title, String content) {
    }

    public record DeletePost(String token, String title) {
    }

    public record GetPosts(int page) {
    }
}
