package com.example.demo.infra.posts;

import com.example.demo.domain.posts.Post;
import com.example.demo.domain.posts.PostRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostRepositoryImpl implements PostRepository {
    private final PostJPARepository postJPARepository;

    public PostRepositoryImpl(PostJPARepository postJPARepository) {
        this.postJPARepository = postJPARepository;
    }

    @Override
    public Optional<Post> savePost(Post post) {
        return Optional.of(postJPARepository.save(post));
    }

    @Override
    public List<Post> getPosts() {
        return postJPARepository.findAll();
    }


}
