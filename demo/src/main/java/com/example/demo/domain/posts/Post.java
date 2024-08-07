package com.example.demo.domain.posts;

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

    public Post(){}

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updateContent(String content) {
        this.content = content;
    }

}
