package org.example;

public class ContentReport {
    private Song reportedSong;
    private User reporter;
    private String reason;

    public ContentReport(Song song, User reporter, String reason) {
        this.reportedSong = song;
        this.reporter = reporter;
        this.reason = reason;
    }

    // Getters
    public Song getReportedSong() { return reportedSong; }
}