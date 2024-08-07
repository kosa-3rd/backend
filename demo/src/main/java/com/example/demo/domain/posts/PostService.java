package com.example.demo.domain.posts;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    public List<Post> getPosts(){
        return postRepository.getPosts();
    }

    public Post savePost(Post post){
        return postRepository.savePost(post).orElseThrow(() -> new RuntimeException("Post save failed"));
    }
}
