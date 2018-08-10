package com.epam.services;

import com.epam.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    void deleteUser(Long id);
    void restoreUser(Long id);

}
