package com.bezkoder.spring.security.postgresql.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.postgresql.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username = ?1 or u.email= ?1")
    User getUserByUsername(String email);

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    User findByResetPasswordToken(String token);
}
