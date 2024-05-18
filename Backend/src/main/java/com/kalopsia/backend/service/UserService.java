package com.kalopsia.backend.service;

import com.kalopsia.backend.entity.Product;
import com.kalopsia.backend.entity.User;
import com.kalopsia.backend.request.LoginRequest;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUser(int id);

    User addUser(User user) throws Exception;

    User findUserByPhoneNumber(LoginRequest loginRequest);
}
