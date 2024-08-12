package com.example.demo.domain.posts;

import java.util.List;
import java.util.Optional;

public interface PostRepository{
    Optional<Post> savePost(Post post);
    List<Post> getPosts();
}
