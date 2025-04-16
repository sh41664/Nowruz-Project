package org.example;

import java.util.HashMap;

public class AuthenticationService {
    private HashMap<String, Account> users = new HashMap<>();

    public void registerUser(Account account) {
        if (account == null || users.containsKey(account.getUsername())) {
            throw new IllegalArgumentException("User already exists or invalid data.");
        }
        users.put(account.getUsername(), account);
    }

    public boolean login(String username, String password) {
        Account account = users.get(username);
        if (account == null) {
            System.out.println("User not found.");
            return false;
        }
        return account.authenticate(password);
    }
}
