package com.example.demo.application;

import com.example.demo.domain.posts.Post;
import com.example.demo.domain.posts.PostCommand;
import com.example.demo.domain.posts.PostService;
import com.example.demo.domain.users.User;
import com.example.demo.domain.users.UserService;
import com.example.demo.interfaces.controller.post.dto.PostInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostProcessor {
    private final UserService userService;
    private final TokenManager tokenManager;
    private final PostService postService;


    public PostProcessor(UserService userService, TokenManager tokenManager, PostService postService) {
        this.userService = userService;
        this.tokenManager = tokenManager;
        this.postService = postService;
    }

    public List<Post> getUserPosts(PostCommand.UserPosts command){
        String token = command.token();
        String userEmail = tokenManager.validateToken(token);
        userService.validateUser(userEmail);
        return userService.getUserPosts(userEmail);
    }
    public List<Post> getAllPosts(){
        return userService.getAllPosts();
    }

    public Post publishPost(PostCommand.CreatePost createPostCommand) {
        String token = createPostCommand.token();
        String userEmail = tokenManager.validateToken(token);
        Post post = new Post(createPostCommand.title(), createPostCommand.content(), createPostCommand.station());
        return userService.savePost(userEmail, post);
    }

    public Post updatePost(PostCommand.UpdatePost updatePostCommand) {
        String token = updatePostCommand.token();
        String userEmail = tokenManager.validateToken(token);
        userService.validateUser(userEmail);
        return userService.updatePost(userEmail, updatePostCommand);
    }

    public Post deletePost(PostCommand.DeletePost deletePostCommand) {
        String token = deletePostCommand.token();
        String userEmail = tokenManager.validateToken(token);
        userService.validateUser(userEmail);
        return userService.deletePost(userEmail, deletePostCommand);
    }

    public Page<PostInfoDTO> getPostsWithSubwayId(PostCommand.GetPostsWithSubwayId userPostComand) {
        return postService.getPosts(userPostComand.page(), userPostComand.subwayId());
    }

    public Page<PostInfoDTO> getPostsWithStation(PostCommand.GetPostsWithStation userPostComand) {
        return postService.getPosts(userPostComand.page(), userPostComand.station());
    }
}
