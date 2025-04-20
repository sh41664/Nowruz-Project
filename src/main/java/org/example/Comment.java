package org.example;

public class Comment {
    private String username;
    private String text;

    public Comment(User username, String text) {
        this.username = String.valueOf(username);
        this.text = text;
    }

    @Override
    public String toString() {
        return username + ": " + text;
    }
}
