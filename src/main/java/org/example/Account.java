package org.example;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Account {
    private String name;
    private String email;
    private String username;
    private String passwordHash;  // Store hashed password only
    private String role;
    private LocalDate joinDate;
    private boolean isVerified;

    public Account(String name, String email, String username, String password, String role) {
        this.name = Objects.requireNonNull(name, "Name cannot be null");
        this.email = validateEmail(email);
        this.username = validateUsername(username);
        this.passwordHash = PasswordUtil.hashPassword(Objects.requireNonNull(password));
        this.role = role;
        this.joinDate = LocalDate.now();
        this.isVerified = false;
    }

    /**
     * Authenticates a user by comparing password hashes
     * @param inputPassword The plaintext password to verify
     * @return true if authentication succeeds
     */
    public boolean authenticate(String inputPassword) {
        return PasswordUtil.verifyPassword(inputPassword, this.passwordHash);
    }

    public abstract void displayProfile();

    // Password validation utilities
    private static class PasswordUtil {
        // In production, use BCrypt with proper salt
        public static String hashPassword(String password) {
            return Integer.toString(password.hashCode()); // Simple example - replace with real hashing
        }

        public static boolean verifyPassword(String input, String storedHash) {
            return hashPassword(input).equals(storedHash);
        }
    }

    // Email validation
    private String validateEmail(String email) {
        if (email == null || !email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        return email;
    }

    // Username validation
    private String validateUsername(String username) {

        if (!username.matches("^[a-zA-Z0-9_]+$")) {
            throw new IllegalArgumentException("Username can only contain letters, numbers, and underscores");
        }
        return username;
    }

    // Password change with validation
    public void changePassword(String oldPassword, String newPassword) {
        if (!authenticate(oldPassword)) {
            throw new SecurityException("Current password is incorrect");
        }
        if (newPassword.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters");
        }
        this.passwordHash = PasswordUtil.hashPassword(newPassword);
    }

    // Getters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getRole() { return role; }
    public LocalDate getJoinDate() { return joinDate; }
    public boolean isVerified() { return isVerified; }

    // Verification
    public void verifyAccount() {
        this.isVerified = true;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - Joined: %s", name, username, joinDate);
    }
}