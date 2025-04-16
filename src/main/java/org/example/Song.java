package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Song {
    private String title;
    private String lyrics;
    private String genre;
    private Artist artist;  // Owner of the song
    private Album album;    // Parent album (can be null)
    private List<Comment> comments = new ArrayList<>();
    private int views;

    public Song(String title, String lyrics, String genre, Artist artist, Album album) {
        this.title = title;
        this.lyrics = lyrics;
        this.genre = genre;
        this.artist = artist;
        this.album = album;
        this.artist.getSongs().add(this);  // Auto-register with artist
        if (album != null) {
            this.album.addSong(this);      // Auto-add to album if provided
        }
    }

    // Artist relationship management
    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist newArtist) {
        if (this.artist != null) {
            this.artist.getSongs().remove(this);  // Remove from old artist
        }
        this.artist = newArtist;
        if (newArtist != null && !newArtist.getSongs().contains(this)) {
            newArtist.getSongs().add(this);  // Add to new artist
        }
    }

    // Album relationship management
    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album newAlbum) {
        if (this.album != null) {
            this.album.getSongs().remove(this);
        }
        this.album = newAlbum;
        if (newAlbum != null && !newAlbum.getSongs().contains(this)) {
            newAlbum.addSong(this);
        }
    }

    // Comment management
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public List<Comment> getComments() {
        return Collections.unmodifiableList(comments);
    }

    // View tracking
    public void incrementViews() {
        views++;
    }

    public int getViews() {
        return views;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return String.format("%s by %s (%s)", title, artist.getName(), genre);
    }

    public void displaySongPage() {

            System.out.println("\n═══════════════════════════════════════");
            System.out.printf("  %s %n", title.toUpperCase());
            System.out.printf("  %s %n", artist.getName());
            System.out.println("═══════════════════════════════════════");
    }
}