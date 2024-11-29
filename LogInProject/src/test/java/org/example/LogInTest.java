package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogInTest {
    UserRepository userRepository;
    SignIn signIn;
    LogIn toTest;

    @BeforeEach
    void setUp() {
        Sha256Hashing sha256Hashing = new Sha256Hashing();
        userRepository = new UserRepository(sha256Hashing);
        signIn = new SignIn(userRepository);
        toTest = new LogIn(userRepository);
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    public void logInUser_validateNotExistentUser_throwsException() {
        // When
        String user = "Alex";
        String pass = "1234";
        // Act

        // Assert
        assertThrows(IllegalArgumentException.class, () -> toTest.logInUser(user, pass));
    }

    @Test
    public void logInUser_validateUser_returnsTrue() {
        // When
        String user = "Alex";
        String pass = "1234";
        // Act
        signIn.signInUser(user, pass);
        // Assert
        assertTrue(toTest.logInUser(user, pass));
    }

    @Test
    public void logInUser_validateUserWithDifferentPassWord_returnsFalse() {
        // When
        String user = "Alex";
        String pass = "1234";
        // Act
        signIn.signInUser(user, pass);
        // Assert
        assertFalse(toTest.logInUser(user, "12"));
    }
}