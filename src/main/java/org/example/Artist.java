package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jdk.internal.icu.lang.UCharacter.getAge;

public class Artist extends Account {
    private List<Song> songs = new ArrayList<>();
    private List<Album> albums = new ArrayList<>();

    public Artist(String name, int age, String email, String username, String password) {
        super(name, email, username, password, "Artist");
    }

    @Override
    public void displayProfile() {
        System.out.println("\n=== Artist Profile ===");
        System.out.println("Name: " + getName());
        System.out.println("Username: " + getUsername());
        System.out.println("Email: " + getEmail());
        System.out.println("Age: " + getAge());
        System.out.println("Role: " + getRole());
        System.out.println("Most Popular Songs: ");
        displayPopularSongs();
        System.out.println("All Songs: ");
        displayAllSongs();
        System.out.println("Albums: ");
        displayAlbums();
    }

    private String getAge() {
        String age = null;
        return age;
    }

    // Edit Lyrics Directly
    public void editLyrics(Song song, String newLyrics) {
        if (songs.contains(song)) {
            song.setLyrics(newLyrics);
            System.out.println("Lyrics for \"" + song.getTitle() + "\" have been updated.");
        } else {
            System.out.println("Error: You can only edit your own songs.");
        }
    }

    // Approve or Reject User-Submitted Edits
    public void reviewEdit(Song song, String newLyrics, boolean approve) {
        if (approve) {
            song.setLyrics(newLyrics);
            System.out.println("Lyric edit for \"" + song.getTitle() + "\" has been approved.");
        } else {
            System.out.println("Lyric edit for \"" + song.getTitle() + "\" has been rejected.");
        }
    }



    // Create New Albums
    public Album createAlbum(String title, String releaseDate) {
        Album newAlbum = new Album(title, releaseDate, this);
        albums.add(newAlbum);
        System.out.println("New album created: " + newAlbum.getTitle());
        return newAlbum;
    }

    // Add Song to Album
    public void addSongToAlbum(Song song, Album album) {
        if (albums.contains(album) && songs.contains(song)) {
            album.addSong(song);
            System.out.println("Song \"" + song.getTitle() + "\" has been added to album \"" + album.getTitle() + "\".");
        } else {
            System.out.println("Error: Either the album or song does not belong to you.");
        }
    }

    // Display Most Popular Songs
    public void displayPopularSongs() {
        songs.stream()
                .sorted((s1, s2) -> Integer.compare(s2.getViews(), s1.getViews()))
                .limit(5)
                .forEach(song -> System.out.println("- " + song.getTitle() + " (" + song.getViews() + " views)"));
    }

    // Display All Songs
    public void displayAllSongs() {
        songs.forEach(song -> System.out.println("- " + song.getTitle()));
    }

    // Display All Albums
    public void displayAlbums() {
        albums.forEach(album -> System.out.println("- " + album.getTitle()));
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public List<Song> getSongs() {
        return songs;
    }

    
}
