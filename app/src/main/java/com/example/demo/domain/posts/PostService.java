package com.example.demo.domain.posts;

import com.example.demo.interfaces.controller.post.dto.PostInfoDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    @Cacheable(value = "userCache", key = "#page", unless = "#result == null")
    public Page<PostInfoDTO> getPosts(int page) {
        return postRepository.getPosts(page);
    }

    @Cacheable(value = "userCache", key = "#subwayId", unless = "#result == null")
    public Page<PostInfoDTO> getPosts(int page, int subwayId) {
        return postRepository.getPosts(page, subwayId);
    }

    @Cacheable(value = "userCache", key = "#station", unless = "#result == null")
    public Page<PostInfoDTO> getPosts(int page, String station) {
        return postRepository.getPosts(page, station);
    }

    @Cacheable(value = "userCache", key = "#email", unless = "#result == null")
    public Page<PostInfoDTO> getPostsWithEmail(int page, String email) {
        return postRepository.getPostsWithEmail(page, email);
    }

    public Post savePost(Post post){
        return postRepository.savePost(post).orElseThrow(() -> new RuntimeException("Post save failed"));
    }
}
