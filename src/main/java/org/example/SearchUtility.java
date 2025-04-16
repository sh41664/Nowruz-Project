package org.example;

import java.util.List;

public class SearchUtility {
    public static Song searchSong(List<Song> songs, String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Search title cannot be empty.");
        }

        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                return song;
            }
        }
        return null;
    }
}
