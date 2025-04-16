package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Album {
    private String title;
    private String releaseDate;
    private Artist artist;
    private List<Song> songs = new ArrayList<>();

    public Album(String title, String releaseDate, Artist artist) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.artist = artist;
        artist.getAlbums().add(this); // Auto-register with artist
    }

    /**
     * Returns an unmodifiable view of the songs in this album
     * @return List of songs (immutable)
     */
    public List<Song> getSongs() {
        return Collections.unmodifiableList(songs);
    }

    /**
     * Adds a song to the album and maintains two-way relationship
     * @param song The song to add
     */
    public void addSong(Song song) {
        if (!songs.contains(song)) {
            songs.add(song);
            if (song.getAlbum() != this) {
                song.setAlbum(this); // Update the song's reference
            }
        }
    }

    /**
     * Removes a song from the album and maintains relationship consistency
     * @param song The song to remove
     */
    public void removeSong(Song song) {
        if (songs.remove(song)) {
            if (song.getAlbum() == this) {
                song.setAlbum(null); // Clear the song's reference
            }
        }
    }

    // Other album methods
    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Artist getArtist() {
        return artist;
    }

    public void displayTracklist() {
        System.out.println("\n=== " + title.toUpperCase() + " TRACKLIST ===");
        songs.forEach(song ->
                System.out.println((songs.indexOf(song) + 1) + ". " + song.getTitle())
        );
    }



    @Override
    public String toString() {
        return String.format("%s - %s (%d songs)",
                artist.getName(), title, songs.size());
    }

    public void displayAlbumPage() {

            System.out.println("\n═══════════════════════════════════════");
            System.out.println("  " + title.toUpperCase());
            System.out.println("  by " + artist.getName());
            System.out.println("═══════════════════════════════════════");

    }
}