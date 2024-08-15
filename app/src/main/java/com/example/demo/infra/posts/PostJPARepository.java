package com.example.demo.infra.posts;

import com.example.demo.domain.posts.Post;
import com.example.demo.interfaces.controller.post.dto.PostInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostJPARepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);

    @Query("select new com.example.demo.interfaces.controller.post.dto.PostInfoDTO(p.id, p.content, p.station, p.createdAt, p.user.nickname)" +
            " from Post p ")
    Page<PostInfoDTO> getPosts(Pageable pageable);

    @Query("select new com.example.demo.interfaces.controller.post.dto.PostInfoDTO(p.id, p.content, p.station, p.createdAt, p.user.nickname)" +
            " from Post p " +
            "where p.station = :station order by p.createdAt desc")
    Page<PostInfoDTO> getPostsWithStation(@Param("station") String station, Pageable pageable);

    @Query("select new com.example.demo.interfaces.controller.post.dto.PostInfoDTO(p.id, p.content, p.station, p.createdAt, p.user.nickname)" +
            "    from Post p where p.station in (" +
            "    select stn.name from Station stn" +
            "    join stn.subway sw" +
            "    where sw.id = :subwayId" +
            ") order by p.createdAt desc")
    Page<PostInfoDTO> getPostsWithSubwayId(@Param("subwayId")int subwayId, Pageable pageable);

    @Query("select new com.example.demo.interfaces.controller.post.dto.PostInfoDTO(p.id, p.content, p.station, p.createdAt, p.user.nickname)" +
            "   from Post p " +
            "   where p.user.userEmail = :email")
    Page<PostInfoDTO> getPostsWithEmail(String email, Pageable pageable);
}
