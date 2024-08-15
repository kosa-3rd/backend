package com.example.demo.interfaces.controller.post;

import com.example.demo.domain.posts.Post;
import com.example.demo.domain.posts.PostCommand;
import com.example.demo.interfaces.controller.post.dto.PostInfoDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class PostMapper{

    public static PostDto.UserPostListResponse toUserPostListResponse(List<Post> posts) {
        return new PostDto.UserPostListResponse(posts);
    }
    public static PostDto.PostListResponse toPostListResponse(Page<PostInfoDTO> posts) {
        return new PostDto.PostListResponse(posts);
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

    public static PostCommand.DeletePost toDeletePostCommand(String token, PostDto.DeletePostRequest request, Long postId) {
        return new PostCommand.DeletePost(token,request.title(), postId);
    }

    public static PostDto.DeletePostResponse toDeletePostResponse(Post post) {
        return new PostDto.DeletePostResponse(post.getTitle());
    }

    public static PostCommand.GetPostsWithSubwayId toGetPostComandWithSubwayId(int page, int subwayId) {
        return new PostCommand.GetPostsWithSubwayId(page, subwayId);
    }

    public static PostCommand.GetPostsWithStation toGetPostComandWithStation(int page, String station) {
        return new PostCommand.GetPostsWithStation(page, station);
    }

    public static PostCommand.GetUserPosts toUserPostComandWithPage(int page, String token) {
        return new PostCommand.GetUserPosts(page, token);
    }

    public static PostCommand.GetUserPosts toGetUserPosts(int page, String token) {
        return new PostCommand.GetUserPosts(page, token);
    }
}
