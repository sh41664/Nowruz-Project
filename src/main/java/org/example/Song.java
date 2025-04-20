package org.example;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

public class Song {
    private String title;
    private String lyrics;
    private String genre;
    private String tags; // Tags like "pop", "rock", etc.
    private int views;
    private String releaseDate;
    private Artist mainArtist; // Primary artist of the song
    private List<Artist> featuredArtists = new ArrayList<>(); // List of featured artists
    private Album album; // Null if the song is a single
    private List<Comment> comments = new ArrayList<>(); // List of comments on the song

    public Song(String title, String lyrics, String genre, String tags, String date, Artist mainArtist, Album album) {
        this.title = title;
        this.lyrics = lyrics;
        this.genre = genre;
        this.tags = tags;
        this.releaseDate = releaseDate;
        this.mainArtist = mainArtist;
        this.album = album;
        this.views = 0; // Views count starts at zero
    }

    public Song(String title, String lyrics, String pop, String introspective, Album album1) {
    }

    // Increment the view count
    public void incrementViews() {
        views++;
    }

    // Add a featured artist
    public void addFeaturedArtist(Artist artist) {
        if (!featuredArtists.contains(artist)) {
            featuredArtists.add(artist);
            System.out.println("Featured artist \"" + artist.getName() + "\" added to the song \"" + title + "\".");
        } else {
            System.out.println("Artist \"" + artist.getName() + "\" is already a featured artist on this song.");
        }
    }

    // Add a comment to the song
    public void addComment(Comment comment) {
        comments.add(comment);
        System.out.println("Comment added to the song \"" + title + "\".");
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getLyrics() {
        return lyrics;
    }

    public String getGenre() {
        return genre;
    }

    public String getTags() {
        return tags;
    }

    public int getViews() {
        return views;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Artist getMainArtist() {
        return mainArtist;
    }

    public List<Artist> getFeaturedArtists() {
        return featuredArtists;
    }

    public Album getAlbum() {
        return album;
    }

    public List<Comment> getComments() {
        return comments;
    }

    // Setters
    public void setLyrics(String newLyrics) {
        this.lyrics = newLyrics;
    }

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", tags='" + tags + '\'' +
                ", views=" + views +
                ", releaseDate='" + releaseDate + '\'' +
                ", mainArtist=" + mainArtist.getName() +
                ", album=" + (album != null ? album.getTitle() : "Single") +
                ", featuredArtists=" + featuredArtists.size() +
                '}';
    }
    public static void displayTopSongs(List<Song> songs) {
        System.out.println("\n=== Top Songs by Views ===");
        songs.stream()
                .sorted((s1, s2) -> Integer.compare(s2.getViews(), s1.getViews())) // Descending order
                .limit(10)
                .forEach(song -> System.out.println(song.getTitle() + " - " + song.getViews() + " views"));
    }

    public void displaySongPage() {
        System.out.println("\n=== Song Page ===");
        System.out.println("Title: " + getTitle());
        System.out.println("Artist: " + getMainArtist().getName());
        System.out.println("Album: " + (album != null ? album.getTitle() : "Single"));
        System.out.println("Genre: " + getGenre());
        System.out.println("Tags: " + getTags());
        System.out.println("Views: " + getViews());
        System.out.println("Lyrics: \n" + getLyrics());

    }

    public Artist getArtist() {
        return mainArtist;
    }

    public org.example.Comment addComment(org.example.Comment comment1) {
        return comment1;


    }

    public void setAlbum(Album album) {
        this.album = album;

    }
}
