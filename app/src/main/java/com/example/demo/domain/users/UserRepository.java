package com.example.demo.domain.users;

import com.example.demo.domain.posts.Post;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> getUser(Long id);

    Optional<User> getUserByEmail(String email);


    Optional<User> saveUser(User user);

    Optional<User> getUserByEmailAndPassword(String username,String password);

    void deleteUser(User user);

    List<Post> getAllPosts();

    List<User>  getAll();

    Optional<User> getUserByPassword(String password);
}
