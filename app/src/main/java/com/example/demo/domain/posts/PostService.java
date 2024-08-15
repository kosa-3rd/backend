package com.example.demo.domain.posts;

import com.example.demo.interfaces.controller.post.dto.PostInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Page<PostInfoDTO> getPosts(int page, int subwayId) {
        return postRepository.getPosts(page, subwayId);
    }

    public Page<PostInfoDTO> getPosts(int page, String station) {
        return postRepository.getPosts(page, station);
    }

    public Page<PostInfoDTO> getPostsWithEmail(int page, String email) {
        return postRepository.getPostsWithEmail(page, email);
    }

    public Post savePost(Post post){
        return postRepository.savePost(post).orElseThrow(() -> new RuntimeException("Post save failed"));
    }
}
