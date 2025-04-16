package org.example;

public class LyricEdit {
    private Song song;
    private User suggestedBy;
    private String newLyrics;

    public LyricEdit(Song song, User suggestedBy, String newLyrics) {
        this.song = song;
        this.suggestedBy = suggestedBy;
        this.newLyrics = newLyrics;
    }

    // Getters
    public Song getSong() { return song; }
    public User getSuggestedBy() { return suggestedBy; }
    public String getNewLyrics() { return newLyrics; }
}