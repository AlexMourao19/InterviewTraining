package org.example;

import com.google.inject.Inject;

public class LogIn {
    public UserRepository userRepository;

    @Inject
    public LogIn(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean logInUser(String username, String password) {
        LogInState validation = userRepository.validateUser(username, password);
        if (validation.equals(LogInState.NOT_EXISTENT)) {
            throw new IllegalArgumentException("User does not exists");
        }
        return validation.equals(LogInState.SUCCESS);
    }
}
