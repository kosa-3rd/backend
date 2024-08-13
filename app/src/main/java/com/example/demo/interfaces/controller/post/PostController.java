package com.example.demo.interfaces.controller.post;

import com.example.demo.application.PostProcessor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/comments")
@RestController
public class PostController {
    private final PostProcessor postProcessor;

    public PostController(PostProcessor postProcessor) {
        this.postProcessor = postProcessor;
    }

    @PostMapping
    public PostDto.CreatePostResponse createPost(@RequestHeader("Authorization") String token,
            @RequestBody PostDto.CreatePostRequest request) {
        return PostMapper.toCreatePostResponse(
                postProcessor.publishPost(PostMapper.toCreatePostCommand(token, request))
        );
    }
    @GetMapping
    public PostDto.GetPostResponse getPost(@RequestHeader("Authorization") String token,
            @RequestBody PostDto.GetPostRequest request) {
        return new PostDto.GetPostResponse(request.title(),"contents");
    }

    @GetMapping("/subway/{subwayId}/{page}/")
    public PostDto.PostListResponse getPostList(
            @PathVariable int subwayId, @PathVariable int page) {
        return PostMapper.toPostListResponse(postProcessor.getPostsWithSubwayId(
                PostMapper.toGetPostComandWithSubwayId(page, subwayId)
        ));
    }

    @GetMapping("/station/{station}/{page}/")
    public PostDto.PostListResponse getPostList(
            @PathVariable int page, @PathVariable String station) {
        return PostMapper.toPostListResponse(postProcessor.getPostsWithStation(
                PostMapper.toGetPostComandWithStation(page, station)
        ));
    }


    @GetMapping("/list")
    public PostDto.UserPostListResponse getUserPostList(@RequestHeader("Authorization") String token) {
        return PostMapper.toUserPostListResponse(postProcessor.getUserPosts(
                PostMapper.toUserPostComand(token)
        ));
    }

    @GetMapping("/all")
    public PostDto.UserPostListResponse getAllPostList() {
        return PostMapper.toUserPostListResponse(postProcessor.getAllPosts());
    }


    @PutMapping("/update/{postId}")
    public PostDto.UpdatePostResponse updatePost(@RequestHeader("Authorization") String token,
            @PathVariable Long postId,
            @RequestBody PostDto.UpdatePostRequest request) {
        return PostMapper.toUpdatePostResponse(
                postProcessor.updatePost(
                        PostMapper.toUpdatePostCommand(token, request, postId)
                )
        );
    }
    @DeleteMapping
    public PostDto.DeletePostResponse deletePost(@RequestHeader("Authorization") String token,
            @RequestBody PostDto.DeletePostRequest request) {
        return PostMapper.toDeletePostResponse(
                postProcessor.deletePost(
                        PostMapper.toDeletePostCommand(token, request)
                )
        );
    }

}
