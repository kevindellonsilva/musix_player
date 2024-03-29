import javax.naming.Name;
import java.util.Scanner;
import java.util.*;

    public class Music_03 {

        static class SongNode  {
            String name;
            SongNode next;
            SongNode prev;
            int playCount;

            SongNode(String songName)
            {
                this.name = songName;
                this.next = null;
                this.prev = null;
                this.playCount = 0;
            }

            public int compareTo(SongNode other) {
                return Integer.compare(other.playCount, this.playCount);
            }

        }


            static class Playlist {
                private SongNode head;
                private SongNode currentSong; // Track the current song

                Playlist() {
                    this.head = null;
                    this.currentSong = null;
                }

                void insertLast(String songName) {
                    SongNode newNode = new SongNode(songName);

                    if (head == null) {
                        head = newNode;
                        currentSong = head; // Set the current song if the playlist was empty
                    } else {
                        SongNode current = head;
                        while (current.next != null) {
                            current = current.next;
                        }
                        current.next = newNode;
                        newNode.prev = current;
                    }
                }

                void insertFirst(String songName) {
                    SongNode newNode = new SongNode(songName);

                    if (head != null) {
                        newNode.next = head;
                        head.prev = newNode;
                    }
                    head = newNode;
                    currentSong = head; // Set the current song after inserting at the beginning
                }

                boolean containsSong(String songName) {
                    SongNode current = head;
                    while (current != null) {
                        if (current.name.equals(songName)) {
                            return true;
                        }
                        current = current.next;
                    }
                    return false;
                }

                void displaySongs() {
                    SongNode current = head;
                    while (current != null) {
                        System.out.println(current.name);
                        current = current.next;
                    }
                }

                void playNextSong() {
                    if (currentSong != null && currentSong.next != null) {
                        currentSong = currentSong.next;
                        currentSong.playCount++;
                        System.out.println("Now playing next song: " + currentSong.name);
                    } else {
                        System.out.println("No next song available.");
                    }
                }

                void playPreviousSong() {
                    if (currentSong != null && currentSong.prev != null) {
                        currentSong = currentSong.prev;
                        currentSong.playCount++;
                        System.out.println("Now playing previous song: " + currentSong.name);
                    } else {
                        System.out.println("No previous song available.");
                    }
                }

                SongNode getHead() {
                    return head;
                }

                void clear() {
                    head = null;
                    currentSong = null;
                }
            }

            static class MusicPlayer {
                private Playlist home;
                private Playlist favorites;
                private Playlist[] playlists;
                private int playlistCount;
                private Scanner scanner;

                hashmap map1;


                MusicPlayer() {
                    home = new Playlist();
                    favorites = new Playlist();
                    playlists = new Playlist[3]; // Assuming there are 3 playlists
                    for (int i = 0; i < 3; i++) {
                        playlists[i] = new Playlist();
                    }
                    playlistCount = 0;
                    scanner = new Scanner(System.in); // Initialize the scanner
                    map1 = new hashmap();

                    // Initialize initial songs
                    initializeSongs();
                }


                // Method to initialize initial songs
                private void initializeSongs() {
                    home.insertLast("song 1");
                    home.insertLast("song 2");
                    home.insertLast("song 3");
                    home.insertLast("Song 4");
                }

               void mostplayed()
               {
                   //get 5 most played songs
                   map1.listMostPlayedSongs(3);
               }

                // Method to display all songs
                private void displayAllSongs() {
                    System.out.println("All Songs:");
                    System.out.println("Home Playlist:");
                    home.displaySongs();
                    System.out.println("Favorites Playlist:");
                    favorites.displaySongs();
                    for (int i = 0; i < playlistCount; i++) {
                        System.out.println("Playlist " + (i + 1) + ":");
                        playlists[i].displaySongs();
                    }
                }

                void displayMenu() {
                    System.out.println("Navigation Pages:");
                    System.out.println("1. HOME");
                    System.out.println("2. Favorites");
                    System.out.println("3. Playlists");
                    System.out.println("4. All Songs");
                    System.out.println("5. most played song");
                    System.out.print("Enter a number to navigate: ");
                }

                void homePage()
                {
                    int choice;
                    do {
                        System.out.println("welcome to Home Page:");
                        System.out.println("1. Add a new song");
                        System.out.println("2. Listen to songs");
                        System.out.println("3. Go to the previous menu");
                        System.out.print("Enter a number to proceed: ");
                        choice = scanner.nextInt();

                        switch (choice) {
                            case 1:
                                addNewSong();
                                break;
                            case 2:
                                listenToSongs();
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Invalid choice. Please enter a valid number.");
                        }
                    } while (choice != 3);
                }

                void addNewSong() {
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter the name of the new song: ");
                    String songName = scanner.nextLine();

                    home.insertLast(songName);
                    System.out.println("Song added to HOME playlist.");
                }

                void listenToSongs() {
                    System.out.println("Listen to Songs:");
                    home.displaySongs();

                    int choice;
                    do {
                        System.out.println("1. Play a song");
                        System.out.println("2. Play next song");
                        System.out.println("3. Play previous song");
                        System.out.println("4. Add to Favorites");
                        System.out.println("5. Add to Playlist");
                        System.out.println("6. Go to the previous menu");
                        System.out.print("Enter a number to proceed: ");
                        choice = scanner.nextInt();

                        switch (choice) {
                            case 1:
                                playSong();
                                break;
                            case 2:
                                home.playNextSong();
                                break;
                            case 3:
                                home.playPreviousSong();
                                break;
                            case 4:
                                addSongToFavorites();
                                break;
                            case 5:
                                addSongToPlaylist();
                                break;
                            case 6:
                                break;
                            default:
                                System.out.println("Invalid choice. Please enter a valid number.");
                        }
                    } while (choice != 6);
                }

                void addSongToFavorites() {
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter the name of the song to add to Favorites: ");
                    String songName = scanner.nextLine();

                    if (home.containsSong(songName)) {
                        if (!favorites.containsSong(songName)) {
                            favorites.insertFirst(songName);
                            System.out.println("Song added to Favorites playlist.");
                        } else {
                            System.out.println("Song is already favorited.");
                        }
                    } else {
                        System.out.println("Song is not available in the Listen to Songs playlist.");
                    }
                }

                void addSongToPlaylist() {
                    displayPlaylists();

                    System.out.print("Enter the number of the playlist to add the song: ");
                    int playlistChoice = scanner.nextInt();

                    if (playlistChoice >= 1 && playlistChoice <= playlistCount) {
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter the name of the song to add to the playlist: ");
                        String songName = scanner.nextLine();

                        if (home.containsSong(songName)) {
                            if (!playlists[playlistChoice - 1].containsSong(songName)) {
                                playlists[playlistChoice - 1].insertFirst(songName);
                                System.out.println("Song added to Playlist " + playlistChoice + ".");
                            } else {
                                System.out.println("Song is already in the playlist.");
                            }
                        } else {
                            System.out.println("Song is not available in the Listen to Songs playlist.");
                        }
                    } else {
                        System.out.println("Invalid playlist choice.");
                    }
                }

                void displayPlaylists() {
                    System.out.println("Available Playlists:");
                    for (int i = 0; i < playlistCount; ++i) {
                        System.out.println((i + 1) + ". Playlist " + (i + 1));
                    }
                }

                void playSong()
                {
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter the name of the song to play: ");
                    String songName = scanner.nextLine();

                    if (home.containsSong(songName))
                    {
                        map1.playSong2(songName);
                        System.out.println("Now playing: " + songName);
                    }
                    else
                    {
                        System.out.println("Song is not in the HOME playlist.");
                    }

                }
                void displayFavorites() {
                    System.out.println("Favorites Page:");
                    System.out.println("1. Display all favorited songs");
                    System.out.println("2. Play a song from Favorites");
                    System.out.println("3. Go to the previous menu");
                    System.out.print("Enter a number to proceed: ");
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            favorites.displaySongs();
                            break;
                        case 2:
                            playSong(); // Call playSong() directly or implement playSongFromFavorites()
                            break;
                        case 3:
                            return;
                        default:
                            System.out.println("Invalid choice. Please enter a valid number.");
                            break;
                    }
                }

                void playlistPage() {
                    System.out.println("Playlists Page:");
                    System.out.println("1. Create a new playlist");
                    System.out.println("2. Display all playlists");
                    System.out.println("3. View songs in a playlist");
                    System.out.println("4. Go to the previous menu");
                    System.out.print("Enter a number to proceed: ");
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            createNewPlaylist();
                            break;
                        case 2:
                            displayPlaylists();
                            break;
                        case 3:
                            // Implement viewPlaylistSongs() method or remove this case
                            System.out.println("Functionality not implemented yet.");
                            break;
                        case 4:
                            return;
                        default:
                            System.out.println("Invalid choice. Please enter a valid number.");
                            break;
                    }
                }

                void createNewPlaylist() {
                    if (playlistCount < 3) { // Assuming there is a limit of 3 playlists
                        playlists[playlistCount].insertLast("Playlist " + (playlistCount + 1));
                        System.out.println("Playlist created: Playlist " + (playlistCount + 1));
                        playlistCount++;
                    } else {
                        System.out.println("Maximum number of playlists reached (3).");
                    }
                }
            }


            public static void main(String[] args) {
                MusicPlayer player = new MusicPlayer();



                int choice;
                do {
                    player.displayMenu();
                    choice = player.scanner.nextInt();

                    switch (choice) {
                        case 1:
                            player.homePage();
                            break;
                        case 2:
                            player.displayFavorites();
                            break;
                        case 3:
                            player.playlistPage();
                            break;
                        case 4:
                            player.displayAllSongs();
                            break;
                        case 5:
                             player.mostplayed();
                        default:
                            System.out.println("Invalid choice. Please enter a valid number.");
                    }
                } while (choice >= 1 && choice <= 3);
            }

        }


