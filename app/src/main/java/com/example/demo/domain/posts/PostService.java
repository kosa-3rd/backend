package com.example.demo.domain.posts;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    public Page<Post> getPosts(int page){
        return postRepository.getPosts(page);
    }

    public Post savePost(Post post){
        return postRepository.savePost(post).orElseThrow(() -> new RuntimeException("Post save failed"));
    }
}
