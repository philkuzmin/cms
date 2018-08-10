package com.epam.services;

import com.epam.model.Roles;
import com.epam.model.States;
import com.epam.model.User;
import com.epam.model.UserForm;
import com.epam.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserForm userForm) {

        String hashPassword = passwordEncoder.encode(userForm.getPassword());
        User user = User.builder()
                .login(userForm.getUsername())
                .password(hashPassword)
                .state(States.ACTIVE)
                .role(Roles.USER)
                .build();
        userRepository.save(user);
    }
}
