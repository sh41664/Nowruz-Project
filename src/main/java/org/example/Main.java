package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<User> users = new ArrayList<>();
    private static final List<Artist> artists = new ArrayList<>();
    private static final List<Song> songs = new ArrayList<>();
    private static final List<Album> albums = new ArrayList<>();
    private static final AuthenticationService authService = new AuthenticationService();

    // Initialize seed data
    public static void initializeSeedData() {
        User user1 = new User("John Doe", 25, "john@example.com", "john_doe", "password123");
        User user2 = new User("Jane Smith", 28, "jane@example.com", "jane_smith", "password456");
        users.add(user1);
        authService.registerUser(user1);
        users.add(user2);
        authService.registerUser(user2);

        Artist artist1 = new Artist("Taylor Swift", 33, "taylor@example.com", "taylor_swift", "artistPass1");
        Artist artist2 = new Artist("Ed Sheeran", 31, "ed@example.com", "ed_sheeran", "artistPass2");
        artists.add(artist1);

        artists.add(artist2);


        Album album1 = artist1.createAlbum("Midnights", "2023-10-21");
        Album album2 = artist2.createAlbum("Subtract", "2023-05-05");
        albums.add(album1);
        albums.add(album2);
        artist1.getAlbums().add(album1);
        artist2.getAlbums().add(album2);

        Song song1 = new Song("Anti-Hero", "It's me, hi, I'm the problem, it's me...", "Pop", "Introspective",  album1);
        Song song2 = new Song("Bad Habits", "My bad habits lead to late nights...", "Pop", "Catchy, Dark",  album2);
        artist1.getSongs().add(song1);
        artist2.getSongs().add(song2);
        artist1.addSongToAlbum(song1, album1);
        artist2.addSongToAlbum(song2, album2);
        songs.add(song1);
        songs.add(song2);

        Comment comment1 = new Comment(user1, "Love this track!");
        Comment comment2 = new Comment(user2, "Amazing lyrics!");
        song1.addComment(comment1);
        song2.addComment(comment2);

    }

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeSeedData();

        while (true) {
            System.out.println("\n=== Genius Navigation Menu ===");
            System.out.println("1. Sign-Up");
            System.out.println("2. Sign-In");
            System.out.println("3. View Account Pages");
            System.out.println("4. View Album Pages");
            System.out.println("5. View Song Pages");
            System.out.println("6. View Charts (Top Songs by Views)");
            System.out.println("7. Search Entities (Artist/Song/Album)");
            System.out.println("8. View Following Artists");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");


            //exception error
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
                continue;
            }
            scanner.nextLine();

            switch (choice) {
                case 1 -> signUp(scanner);
                case 2 -> {
                    boolean loggedInAccount = signIn(scanner);
                    if (loggedInAccount) {
                        System.out.println("Welcome, " + true + "!");
                    }
                }
                case 3 -> {
                    users.forEach(User::displayAccountPage);
                    artists.forEach(Artist::displayProfile);
                }
                case 4 -> albums.forEach(Album::displayAlbumPage);
                case 5 -> songs.forEach(Song::displaySongPage);
                case 6 -> Song.displayTopSongs(songs);
                case 7 -> {
                    System.out.print("Enter search query: ");
                    searchEntities(scanner.nextLine(), artists, songs, albums);
                }
                case 8 -> users.forEach(User::viewFollowedArtists);
                case 9 -> {
                    System.out.println("Exiting...!");
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");

            }

        }



    }




    // Sign-Up Method
    private static void signUp(Scanner scanner) {
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }

        System.out.print("Age: ");
        int age;
        try {
            age = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid age! Please enter a number.");
            scanner.nextLine();
            return;
        }
        scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        if (email.isEmpty()) {
            System.out.println("Email cannot be empty!");
            return;
        }

        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        if (username.isEmpty()) {
            System.out.println("Username cannot be empty!");
            return;
        }

        System.out.print("Password: ");
        String password = scanner.nextLine().trim();
        if (password.isEmpty()) {
            System.out.println("Password cannot be empty!");
            return;
        }

        System.out.println("Select Role: 1. User 2. Artist");
        int roleChoice;
        try {
            roleChoice = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid role choice!");
            scanner.nextLine();
            return;
        }
        scanner.nextLine();

        if (roleChoice == 1) {
            User newUser = new User(name, age, email, username, password);
            users.add(newUser);

            System.out.println("User account created successfully!");
        } else if (roleChoice == 2) {
            Artist newArtist = new Artist(name, age, email, username, password);
            artists.add(newArtist);

            System.out.println("Artist account created successfully!");
        } else {
            System.out.println("Invalid role choice. Returning to menu.");
        }
    }

    // Sign-In Method
    private static boolean signIn(Scanner scanner) {
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();
        return authService.login(username, password);
    }

    // Search Entities Method
    public static void searchEntities(String query, List<Artist> artists, List<Song> songs, List<Album> albums) {
        System.out.println("\n=== Search Results for: " + query + " ===");

        System.out.println("\nArtists:");
        artists.stream()
                .filter(artist -> artist.getName().toLowerCase().contains(query.toLowerCase()))
                .forEach(artist -> System.out.println("- " + artist.getName()));

        System.out.println("\nSongs:");
        songs.stream()
                .filter(song -> song.getTitle().toLowerCase().contains(query.toLowerCase()))
                .forEach(song -> System.out.println("- " + song.getTitle()));

        System.out.println("\nAlbums:");
        albums.stream()
                .filter(album -> album.getTitle().toLowerCase().contains(query.toLowerCase()))
                .forEach(album -> System.out.println("- " + album.getTitle()));
    }
}
