package org.example;

import com.google.inject.Inject;

public class SignIn {
    private final UserRepository userRepository;

    @Inject
    public SignIn(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void validateCredentials(String userName, String passWord) {
        if (userName.isEmpty()) {
            throw new IllegalArgumentException("User Name is a obligatory field");
        }
        if (passWord.length() < 4) {
            throw new IllegalArgumentException("Password must be at least 4 characters long");
        }
    }

    public boolean signInUser(String username, String password) {
        validateCredentials(username, password);
        if (userRepository.hasUser(username)) {
            throw new IllegalArgumentException(String.format("User already exists: %s", username));
        }
        return userRepository.addUser(username, password);
    }
}
