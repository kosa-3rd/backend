package com.example.demo.infra.posts;

import com.example.demo.domain.posts.Post;
import com.example.demo.domain.posts.PostRepository;
import com.example.demo.interfaces.controller.post.dto.PostInfoDTO;
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
    public Page<PostInfoDTO> getPosts(int page, String station) {
        Pageable pageable = PageRequest.of(page, 15, Sort.by("createdAt").descending());
        return postJPARepository.getPostsWithStation(station, pageable);
    }

    @Override
    public Page<PostInfoDTO> getPosts(int page, int subwayId) {
        Pageable pageable = PageRequest.of(page, 15, Sort.by("createdAt").descending());
        return postJPARepository.getPostsWithSubwayId(subwayId, pageable);
    }




}
