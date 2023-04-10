package com.bezkoder.spring.security.postgresql.service;


import com.bezkoder.spring.security.postgresql.models.User;
import com.bezkoder.spring.security.postgresql.repository.UserRepository;

import com.bezkoder.spring.security.postgresql.security.services.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email : " + email)
                );

        return UserDetailsImpl.build(user);
    }



    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).get();


        return UserDetailsImpl.build(user);
    }
    public void updateResetPasswordToken(String token, String email)  {
        User user = userRepository.findByEmail(email).orElse(null); //maybe here?
        if (user != null) {
            user.setResetPasswordToken(token);
            userRepository.save(user);
        }
    }
}
