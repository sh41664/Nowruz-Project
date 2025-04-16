package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Admin extends Account {
    private int age;
    private List<Account> managedAccounts = new ArrayList<>();
    private List<ContentReport> contentReports = new ArrayList<>();

    public Admin(String name, int age, String email, String username, String password) {
        super(name, email, username, password, "Admin");
        this.age = age;  // Age stored separately from Account
    }

    /**
     * Gets the admin's age
     * @return age in years
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the admin's age with validation
     * @param age Must be between 18 and 100
     */
    public void setAge(int age) {
        if (age < 18 || age > 100) {
            throw new IllegalArgumentException("Admin age must be between 18 and 100");
        }
        this.age = age;
    }

    // Account Management
    public void suspendAccount(Account account) {
        managedAccounts.remove(account);
        System.out.println("Suspended account: " + account.getUsername());
    }

    public void verifyArtist(Artist artist) {
        artist.verifyAccount();
        System.out.println("Verified artist: " + artist.getUsername());
    }

    // Content Moderation
    public void removeSong(Song song) {
        song.getArtist().getSongs().remove(song);
        System.out.println("Removed song: " + song.getTitle());
    }

    public void resolveReport(ContentReport report, boolean takeAction) {
        if (takeAction) removeSong(report.getReportedSong());
        contentReports.remove(report);
    }

    // System Oversight
    public List<Account> getManagedAccounts() {
        return Collections.unmodifiableList(managedAccounts);
    }

    public List<ContentReport> getContentReports() {
        return Collections.unmodifiableList(contentReports);
    }

    @Override
    public void displayProfile() {
        System.out.println("\n=== ADMIN PROFILE ===");
        System.out.println("Name: " + getName());
        System.out.println("Age: " + age);
        System.out.println("Managed Accounts: " + managedAccounts.size());
        System.out.println("Pending Reports: " + contentReports.size());
    }
}