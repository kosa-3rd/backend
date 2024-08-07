package com.example.demo.domain.users;

import com.example.demo.domain.posts.Post;
import com.example.demo.domain.posts.PostCommand;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User getUser(Long id) {
        return userRepository.getUser(id).orElseThrow(
                ()-> new RuntimeException("사용자 정보를 찾을 수 없습니다.")
        );
    }


    @Transactional
    public User login(String username, String password) {
        return userRepository.getUserByEmailAndPassword(username,password).orElseThrow(
                ()-> new RuntimeException("사용자 정보를 찾을 수 없습니다.")
        );
    }

    public void validateUser(String email) {
        userRepository.getUserByEmail(email).ifPresent(
                user -> {
                    throw new RuntimeException("이미 가입된 사용자입니다.");
                }
        );
    }

    @Transactional
    public User saveUser(User user) {
        return userRepository.saveUser(user).orElseThrow(
                ()-> new RuntimeException("사용자 정보를 찾을 수 없습니다.")
        );

    }

    @Transactional
    public User deleteUser(String email) {
        return userRepository.getUserByEmail(email).map(
                user -> {
                    userRepository.deleteUser(user);
                    return user;
                }
        ).orElseThrow(
                ()-> new RuntimeException("사용자 정보를 찾을 수 없습니다.")
        );
    }

    @Transactional
    public List<Post> getUserPosts(String userEmail) {
        return userRepository.getUserByEmail(userEmail).map(
                User::getPosts
        ).orElseThrow(
                ()-> new RuntimeException("사용자 정보를 찾을 수 없습니다.")
        );
    }

    @Transactional
    public Post savePost(String userEmail, Post post) {
        return userRepository.getUserByEmail(userEmail).map(
                user -> {
                    user.addPosts(post);
                    userRepository.saveUser(user);
                    return post;
                }
        ).orElseThrow(
                ()-> new RuntimeException("사용자 정보를 찾을 수 없습니다.")
        );
    }

    public List<Post> getAllPosts() {
        return userRepository.getAllPosts();
    }

    @Transactional
    public Post updatePost(String userEmail, PostCommand.UpdatePost updatePostCommand) {
        return userRepository.getUserByEmail(userEmail).map(
                user -> {
                    Post post = user.getPosts().stream()
                            .filter(p -> p.getTitle().equals(updatePostCommand.title()))
                            .findFirst().orElseThrow(
                                    ()-> new RuntimeException("게시물을 찾을 수 없습니다.")
                            );
                    post.updateContent(updatePostCommand.content());
                    userRepository.saveUser(user);
                    return post;
                }
        ).orElseThrow(
                ()-> new RuntimeException("사용자 정보를 찾을 수 없습니다.")
        );
    }

    @Transactional
    public Post deletePost(String userEmail, PostCommand.DeletePost deletePostCommand) {
        return userRepository.getUserByEmail(userEmail).map(
                user -> {
                    Post post = user.getPosts().stream()
                            .filter(p -> p.getTitle().equals(deletePostCommand.title()))
                            .findFirst().orElseThrow(
                                    ()-> new RuntimeException("게시물을 찾을 수 없습니다.")
                            );
                    user.removePost(post);
                    userRepository.saveUser(user);
                    return post;
                }
        ).orElseThrow(
                ()-> new RuntimeException("사용자 정보를 찾을 수 없습니다.")
        );
    }

    @Transactional
    public User getUserByEmail(String userEmail) {
        return userRepository.getUserByEmail(userEmail).orElseThrow(
                ()-> new RuntimeException("사용자 정보를 찾을 수 없습니다.")
        );
    }
}
