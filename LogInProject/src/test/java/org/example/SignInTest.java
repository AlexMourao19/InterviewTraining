package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignInTest {
    SignIn toTest;
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        Sha256Hashing sha256Hashing = new Sha256Hashing();
        userRepository = new UserRepository(sha256Hashing);
        toTest = new SignIn(userRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void sigIn_enterAValidUser_returnsTrue() {
        // When
        String userName = "Alex";
        String passWord = "1234";
        // Act
        // Assert
        assertTrue(toTest.signInUser(userName, passWord));
    }

    @Test
    public void signIn_withSameUserName_returnsFalse() {
        // When
        String userName1 = "Alex";
        String passWord1 = "1234";
        String userName2 = "Alex";
        String passWord2 = "1234";
        // Act
        boolean validUser1 = toTest.signInUser(userName1, passWord1);
        boolean validUser2 = toTest.signInUser(userName2, passWord2);
        // Assert
        assertFalse(validUser2);
    }


}