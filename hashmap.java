import java.util.*;

public class hashmap {
    private Map<String, Integer> playCountMap;

    public hashmap() {
        playCountMap = new HashMap<>();
    }

    public void playSong2(String songName) {
        playCountMap.put(songName, playCountMap.getOrDefault(songName, 0) + 1);

    }

    public void listMostPlayedSongs(int n) {
        System.out.println("Most Played Songs:");

        playCountMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(n)
                .forEach(entry -> System.out.println(entry.getKey() + " - Plays: " + entry.getValue()));
    }

    /*public static void main(String[] args) {
        hashmap musicPlayer = new hashmap();

        // Simulate playing some songs
        musicPlayer.playSong("Song1");
        musicPlayer.playSong("Song2");
        musicPlayer.playSong("Song1");
        musicPlayer.playSong("Song3");
        musicPlayer.playSong("Song2");
        musicPlayer.playSong("Song1");
        musicPlayer.playSong("Song4");
        musicPlayer.playSong("Song2");

        // List the top 3 most played songs
        musicPlayer.listMostPlayedSongs(3);
    }*/
}

