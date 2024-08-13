package com.example.demo.interfaces.controller.post;

import com.example.demo.domain.posts.Post;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;

import java.util.List;

@UtilityClass
public class PostDto {
    public record CreatePostRequest(String title, String content) {
    }
    public record CreatePostResponse(String title){
    }
    public record UpdatePostRequest(String title, String content) {
    }
    public record UpdatePostResponse(String title, String content){
    }
    public record DeletePostRequest(String title) {
    }
    public record DeletePostResponse(String title){
    }
    public record GetPostRequest(String title) {
    }
    public record GetPostResponse(String title, String content){
    }

    public record UserPostListResponse(List<Post> posts) { }
    public record PostListResponse(Page<Post> posts) { }
}
