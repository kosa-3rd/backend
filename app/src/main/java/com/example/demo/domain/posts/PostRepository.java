package com.example.demo.domain.posts;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PostRepository{
    Optional<Post> savePost(Post post);
    Page<Post> getPosts(int page);
}
