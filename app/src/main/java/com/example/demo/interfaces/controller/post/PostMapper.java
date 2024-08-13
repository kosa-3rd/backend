package com.example.demo.interfaces.controller.post;

import com.example.demo.domain.posts.Post;
import com.example.demo.domain.posts.PostCommand;

import java.util.List;

public class PostMapper{

    public static PostDto.UserPostListResponse toUserPostListResponse(List<Post> posts) {
        return new PostDto.UserPostListResponse(posts);
    }

    public static PostCommand.UserPosts toUserPostComand(String token) {
        return new PostCommand.UserPosts(token);
    }

    public static PostCommand.UpdatePost toUpdatePostCommand(String token, PostDto.UpdatePostRequest request, Long postId) {
        return new PostCommand.UpdatePost(token, request.title(), request.content(),postId);
    }

    public static PostDto.UpdatePostResponse toUpdatePostResponse(Post post) {
        return new PostDto.UpdatePostResponse(post.getTitle(), post.getContent());
    }

    public static PostDto.CreatePostResponse toCreatePostResponse(Post post) {
        return new PostDto.CreatePostResponse(post.getTitle());
    }

    public static PostCommand.CreatePost toCreatePostCommand(String token, PostDto.CreatePostRequest request) {
        return new PostCommand.CreatePost(token, request.title(), request.content(), request.station());
    }

    public static PostCommand.DeletePost toDeletePostCommand(String token, PostDto.DeletePostRequest request) {
        return new PostCommand.DeletePost(token,request.title());
    }

    public static PostDto.DeletePostResponse toDeletePostResponse(Post post) {
        return new PostDto.DeletePostResponse(post.getTitle());
    }
}
