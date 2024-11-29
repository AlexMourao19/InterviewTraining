package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {
    UserRepository toTest;

    @BeforeEach
    void setUp() {
        Sha256Hashing sha256Hashing = new Sha256Hashing();
        toTest = new UserRepository(sha256Hashing);
    }

    @AfterEach
    void tearDown() {
    }

}