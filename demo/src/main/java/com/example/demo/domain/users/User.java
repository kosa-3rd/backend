package com.example.demo.domain.users;

import com.example.demo.domain.posts.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String userEmail;

    @Setter
    @Getter
    private String username;

    @Setter
    @Getter
    private String password;

    @Getter
    private boolean isDeleted;

    @Getter
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    public User() {
    }
    public User(String userEmail, String username,String password) {
        this.userEmail = userEmail;
        this.username = username;
        this.password = password;
        this.posts = new ArrayList<>();
    }
    public User addPosts(Post post) {
        if(posts == null) {
            posts = new ArrayList<>();
        }
        posts.add(post);
        return this;
    }
    public User removePosts(Post post) {
        if(posts == null) {
            posts = new ArrayList<>();
        }
        posts.remove(post);
        return this;
    }


    public void setDeleted(boolean b) {
        this.isDeleted = b;
    }

    public void removePost(Post post) {
        posts.remove(post);
    }

}
