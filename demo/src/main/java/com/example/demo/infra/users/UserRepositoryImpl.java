package com.example.demo.infra.users;

import com.example.demo.domain.posts.Post;
import com.example.demo.domain.users.User;
import com.example.demo.domain.users.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserJPARepository userJPARepository;

    public UserRepositoryImpl(UserJPARepository userJPARepository) {
        this.userJPARepository = userJPARepository;
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userJPARepository.findById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userJPARepository.findByUserEmail(email);
    }

    @Override
    public Optional<User> saveUser(User user) {
        return Optional.of(userJPARepository.save(user));
    }

    @Override
    public Optional<User> getUserByEmailAndPassword(String username, String password) {
        return userJPARepository.findByUserEmailAndUserPassword(username, password);
    }

    @Override
    public void deleteUser(User user) {
        user.setDeleted(true);
        userJPARepository.save(user);
    }

    @Override
    public List<Post> getAllPosts() {
        return userJPARepository.findAll().stream()
                .flatMap(user -> user.getPosts().stream())
                .toList();
    }
}
