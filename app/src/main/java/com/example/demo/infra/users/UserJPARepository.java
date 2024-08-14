package com.example.demo.infra.users;

import com.example.demo.domain.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserJPARepository extends JpaRepository<User, Long> {

    Optional<User> findByUserEmail(String email);

    @Query("SELECT u FROM User u WHERE u.userEmail = :username AND u.password = :password")
    Optional<User> findByUserEmailAndUserPassword(String username, String password);

    @Query("SELECT u FROM User u WHERE u.userEmail =:userEmail AND u.password = :password")
    Optional<User> findByUserPassword(String userEmail, String password);
}
