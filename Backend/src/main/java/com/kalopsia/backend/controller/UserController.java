package com.kalopsia.backend.controller;

import com.kalopsia.backend.entity.User;
import com.kalopsia.backend.request.LoginRequest;
import com.kalopsia.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable int userId) {
        logger.info("User id: {}", userId);
        return userService.getUser(userId);
    }

    @PostMapping("/signup")
    public ResponseEntity<User> addUser(@RequestBody User user) throws Exception {
        logger.info("NEW_USER : {}", user);
        User dbUser = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(dbUser);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody LoginRequest loginRequest) {
        User user = userService.findUserByPhoneNumber(loginRequest);
        logger.info("LOGIN_USER : {}", user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}
