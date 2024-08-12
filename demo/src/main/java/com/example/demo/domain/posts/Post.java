package com.example.demo.domain.posts;

import com.example.demo.domain.users.User;
import jakarta.persistence.*;
import lombok.Getter;

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
    @ManyToOne
    private User user;

    public Post(){}

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updateContent(String content) {
        this.content = content;
    }

}
