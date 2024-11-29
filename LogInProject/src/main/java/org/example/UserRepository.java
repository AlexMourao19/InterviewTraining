package org.example;

import com.google.inject.Inject;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private final Map<String, String> userTable = new HashMap<>();
    private final PassWordHashing passWordHashing;

    @Inject
    public UserRepository(PassWordHashing passWordHashing) {
        this.passWordHashing = passWordHashing;
    }

    public boolean addUser(String userName, String passWord) {
        if (userTable.containsKey(userName)) {
            return false;
        }
        String hashedPassword = passWordHashing.hashPassword(passWord);
        userTable.put(userName, hashedPassword);
        return true;
    }

    public LogInState validateUser(String userName, String passWord) {
        if (!userTable.containsKey(userName)) {
            return LogInState.NOT_EXISTENT;
        }
        String hashedPassword = this.passWordHashing.hashPassword(passWord);
        String userPassWord = userTable.get(userName);
        return hashedPassword.equals(userPassWord) ? LogInState.SUCCESS : LogInState.WRONG_PASSWORD;
    }

    public boolean hasUser(String user) {
        return userTable.containsKey(user);
    }
}
