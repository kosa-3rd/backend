package com.example.demo.domain.posts;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PostCommand {

    public record UserPosts (String token) {
    }

    public record UpdatePost(String token, String title, String content, Long postId) {
    }

    public record CreatePost(String token, String title, String content, String station) {
    }

    public record DeletePost(String token, String title, Long postId) {
    }

    public record GetPostsWithSubwayId(int page, int subwayId) {
    }

    public record GetPostsWithStation(int page, String station) {
    }
}
