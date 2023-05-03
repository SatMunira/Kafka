package com.bezkoder.spring.security.postgresql.security.services;

import com.bezkoder.spring.security.postgresql.models.enums.AuthProvider;
import com.bezkoder.spring.security.postgresql.models.enums.ERole;
import com.bezkoder.spring.security.postgresql.models.User;
import com.bezkoder.spring.security.postgresql.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void processOAuthPostLogin(String username,String thisemail) {

        User user = userRepository.getUserByUsername(thisemail);
        if (user == null) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setEmail(thisemail);
            newUser.setProvider(AuthProvider.google);
            newUser.getRoles().add(ERole.ROLE_USER);


            userRepository.save(newUser);
            System.out.println("IN DB NO SUCH USER");
            System.out.println("Created new user: " + username);
        }


    }
}
