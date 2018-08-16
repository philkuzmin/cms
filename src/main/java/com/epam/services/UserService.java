package com.epam.services;

import com.epam.model.User;
import com.epam.model.UserForm;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User getUserById(Long id);
    void deleteUser(Long id);
    void restoreUser(Long id);
    void updateUser(UserForm userForm, User user);

}
