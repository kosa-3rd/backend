package com.example.demo.domain.posts;

import com.example.demo.domain.users.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String title;

    @Getter
    private String content;

    @Getter
    @Setter
    @ManyToOne
    private User user;

    @Getter
    private String station;

    @CreationTimestamp
    @Column(name="created_at")
    private LocalDateTime createdAt;

    public Post(){}

    public Post(String title, String content, String station) {
        this.title = title;
        this.content = content;
        this.station = station;
    }

    public void updateContent(String content) {
        this.content = content;
    }

}
