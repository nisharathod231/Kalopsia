package com.kalopsia.backend.service;

import com.kalopsia.backend.entity.Product;
import com.kalopsia.backend.entity.User;
import com.kalopsia.backend.exception.NotFoundException;
import com.kalopsia.backend.exception.UnAuthorisedException;
import com.kalopsia.backend.repository.ProductRepository;
import com.kalopsia.backend.repository.UserRepository;
import com.kalopsia.backend.request.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    private List<User> users;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        users = new ArrayList<>();
        users.add(new User(1, "9876543219", "Test-Name-1", "Test-Pass-1"));
        users.add(new User(2, "9876543218", "Test-Name-2", "Test-Pass-2"));
    }

    @Test
    public void shouldReturnUserWhenUserExistsForGivenPhoneNumber() {
        LoginRequest loginRequest = new LoginRequest("9876543218", "Test-Pass-2");
        User expectedUser = new User(2, "9876543218", "Test-Name-2", "Test-Pass-2");
        when(userRepository.findByPhoneNumber("9876543218")).thenReturn(expectedUser);

        User actualUser = userService.findUserByPhoneNumber(loginRequest);

        assertNotNull(actualUser);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenInvalidPhoneNumberIdGiven() {
        LoginRequest loginRequest = new LoginRequest("9876543217", "Test-Pass-2");
        when(userRepository.findByPhoneNumber("9876543217")).thenReturn(null);

        assertThrows(NotFoundException.class, () -> userService.findUserByPhoneNumber(loginRequest));
    }

    @Test
    public void shouldThrowUnAuthorisedExceptionWhenInvalidPhoneNumberIdGiven() {
        LoginRequest loginRequest = new LoginRequest("9876543218", "Incorrect-Test-Pass");
        User expectedUser = new User(2, "9876543218", "Test-Name-2", "Test-Pass-2");
        when(userRepository.findByPhoneNumber("9876543218")).thenReturn(expectedUser);

        assertThrows(UnAuthorisedException.class, () -> userService.findUserByPhoneNumber(loginRequest));
    }

}
