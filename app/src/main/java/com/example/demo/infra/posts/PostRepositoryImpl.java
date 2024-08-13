package com.example.demo.infra.posts;

import com.example.demo.domain.posts.Post;
import com.example.demo.domain.posts.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<Post> getPosts(int page) {
        Pageable pageable = PageRequest.of(page, 15, Sort.by("createdAt").descending());
        return postJPARepository.findAllByOrderByCreatedAtDesc(pageable);
    }


}
