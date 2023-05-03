package com.bezkoder.spring.security.postgresql.service;

import com.bezkoder.spring.security.postgresql.models.User;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.EntityManagerFactory;

public interface UserService {

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    UserDetails loadUserById(Long id);
    User getByResetPasswordToken(String Token);
    void updatePassword(User user, String password);
}
