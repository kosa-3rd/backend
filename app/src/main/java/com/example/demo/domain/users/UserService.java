package com.example.demo.domain.users;

import com.example.demo.domain.posts.Post;
import com.example.demo.domain.posts.PostCommand;
import jakarta.persistence.EntityManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    private final UserRepository userRepository;
    private final EntityManager em;

    public UserService(UserRepository userRepository, EntityManager em) {
        this.userRepository = userRepository;
        this.em = em;
    }

    @Transactional
    public User getUser(Long id) {
        return userRepository.getUser(id).orElseThrow(
                ()-> new RuntimeException("사용자 정보를 찾을 수 없습니다.")
        );
    }


    @Transactional
    public User login(String username, String password) {
        User user = userRepository.getUserByEmailAndPassword(username, password).orElseThrow(
                () -> new RuntimeException("사용자 정보를 찾을 수 없습니다.")
        );
        em.flush();
        return user;
    }

    @Transactional
    public User validateUser(String email) {
        return userRepository.getUserByEmail(email).orElseThrow(
                ()-> new RuntimeException("wow wrong")
        );
    }
    @Transactional
    public boolean checkUser(String email){
        return userRepository.getUserByEmail(email).isEmpty();
    }
    
    @Transactional
    public boolean validateCheck(String email){
        Optional<User> user =userRepository.getUserByEmail(email);
        return user.isEmpty();
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
        Optional<User> userByEmail = userRepository.getUserByEmail(userEmail);
        User user = userByEmail.orElseThrow(() -> new RuntimeException("exception"));
        post.setUser(user);
        user.addPosts(post);
        userRepository.saveUser(user);
        return post;
    }

    public List<Post> getAllPosts() {
        return userRepository.getAllPosts();
    }

    @Transactional
    @CacheEvict(cacheNames = "userCache", key = "#userEmail")
    public Post updatePost(String userEmail, PostCommand.UpdatePost updatePostCommand) {
        return userRepository.getUserByEmail(userEmail).map(
                user -> {
                    Post post = user.getPosts().stream()
                            .filter(p -> p.getId().equals(updatePostCommand.postId()))
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
                            .filter(p -> p.getId().equals(deletePostCommand.postId()))
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

    @Transactional
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Transactional
    public User getUserByPassword(String userEmail, String password) {
        return userRepository.getUserByPassword(userEmail,password).orElseThrow(
                ()-> new RuntimeException("사용자 정보를 찾을 수 없습니다.")
        );
    }
}
