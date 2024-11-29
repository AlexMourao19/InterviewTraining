package org.example;

import com.google.inject.Inject;

public class AuthenticationService {
    private final SignIn signIn;
    private final LogIn logIn;

    @Inject
    public AuthenticationService(SignIn signIn, LogIn logIn) {
        this.signIn = signIn;
        this.logIn = logIn;
    }

    public boolean signIn(String username, String password) {
        return signIn.signInUser(username, password);
    }

    public boolean logIn(String username, String password) {
        return logIn.logInUser(username, password);
    }
}