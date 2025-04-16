package org.example;

public class Comment {
    private String username;
    private String text;

    public Comment(User username, String text) {
        if (username == null || username.isEmpty() || text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Username and comment text cannot be empty.");
        }
        this.username = String.valueOf(username);
        this.text = text;
    }

    @Override
    public String toString() {
        return username + ": " + text;
    }
}
