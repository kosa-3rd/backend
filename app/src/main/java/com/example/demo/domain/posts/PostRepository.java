package com.example.demo.domain.posts;

import com.example.demo.interfaces.controller.post.dto.PostInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository{
    Optional<Post> savePost(Post post);

    Page<PostInfoDTO> getPosts(int page, String station);

    Page<PostInfoDTO> getPosts(int page, int subwayId);
}
