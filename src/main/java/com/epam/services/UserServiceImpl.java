package com.epam.services;

import com.epam.model.States;
import com.epam.model.User;
import com.epam.model.UserForm;
import com.epam.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll(new Sort(Sort.Direction.ASC, "id"));
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.getOne(id);
        user.setState(States.DELETED);
        userRepository.saveAndFlush(user);
    }

    @Override
    public void restoreUser(Long id) {
        User user = userRepository.getOne(id);
        user.setState(States.ACTIVE);
        userRepository.saveAndFlush(user);
    }

    @Override
    public void updateUser(UserForm userForm, User user) {

        String password = userForm.getPassword();

        if (password != null && !password.isEmpty()) {
            user.setPassword(passwordEncoder.encode(password));
        }
        user.setLogin(userForm.getUsername());
        userRepository.saveAndFlush(user);
    }
}
