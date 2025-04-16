package org.example;


import java.util.ArrayList;
import java.util.List;

import static jdk.internal.icu.lang.UCharacter.getAge;

public class User extends Account {
    private List<Artist> followedArtists = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();

    public User(String name, int age, String email, String username, String password) {
        super(name, email, username, password, "User");
    }

    @Override
    public void displayProfile() {
        System.out.println("\n=== User Profile ===");
        System.out.println("Name: " + getName());
        System.out.println("Username: " + getUsername());
        System.out.println("Email: " + getEmail());
        System.out.println("Age: " + getAge());
        System.out.println("Role: " + getRole());
        System.out.println("Followed Artists: " + followedArtists.size());
        System.out.println("Comments Made: " + comments.size());
    }

    private String getAge() {
        return String.valueOf(getAge());
    }


    // View Song Lyrics
    public void viewLyrics(Song song) {
        System.out.println("\n=== Viewing Lyrics for: " + song.getTitle() + " ===");
        System.out.println(song.getLyrics());
    }

    // Suggest Edits
    public void suggestEdit(Song song, String newLyrics) {
        System.out.println("\n=== Suggesting Edit for: " + song.getTitle() + " ===");
        System.out.println("Proposed New Lyrics: " + newLyrics);
        // Logic for notifying the respective artist can be implemented
    }

    // Follow Artists
    public void followArtist(Artist artist) {
        if (!followedArtists.contains(artist)) {
            followedArtists.add(artist);
            System.out.println("Started following artist: " + artist.getName());
        } else {
            System.out.println("You are already following: " + artist.getName());
        }
    }

    // View Followed Artists
    public void viewFollowedArtists() {
        System.out.println("\n=== Followed Artists ===");
        for (Artist artist : followedArtists) {
            System.out.println("- " + artist.getName());
        }
    }


    // Comment on a Song
    public void commentOnSong(Song song, String text) {
        Comment comment = new Comment(this, text);
        song.addComment(comment);
        comments.add(comment);
        System.out.println("Comment added to song: " + song.getTitle());
    }

    public void displayAccountPage() {
    }

    public boolean isEmpty() {
        return comments.isEmpty() && followedArtists.isEmpty();

    }
}
