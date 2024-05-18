package com.kalopsia.backend.service;

import com.kalopsia.backend.entity.User;
import com.kalopsia.backend.exception.ConflictException;
import com.kalopsia.backend.exception.NotFoundException;
import com.kalopsia.backend.exception.UnAuthorisedException;
import com.kalopsia.backend.repository.UserRepository;
import com.kalopsia.backend.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User addUser(User user) throws Exception {
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            String exceptionRootCause = e.getRootCause().getMessage();
            if ( exceptionRootCause.contains("Duplicate entry") && exceptionRootCause.contains("phone_number") ) {
                String errorMessage = "Phone number already exists.";
                throw new ConflictException(errorMessage, e);
            } else {
                throw new Exception(e.getMessage());
            }
        }
    }

    @Override
    public User findUserByPhoneNumber(LoginRequest loginRequest) {
        User user = userRepository.findByPhoneNumber(loginRequest.getPhoneNumber());
        if (user == null) {
            throw new NotFoundException("Phone Number not registered.");
        }
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new UnAuthorisedException("Password in incorrect.");
        }
        return user;
    }
}
