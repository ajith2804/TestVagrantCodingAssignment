import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class RecentlyPlayedStore {
    private final int capacity;
    private final Map<User, LinkedList<Song>> userSongMap;

    public RecentlyPlayedStore(int capacity) {
        this.capacity = capacity;
        this.userSongMap = new HashMap<>();
    }

    public void addPlay(User user, Song song) {
        userSongMap.putIfAbsent(user, new LinkedList<>());
        LinkedList<Song> songList = userSongMap.get(user);

        // Add the song to the end of the list
        songList.addLast(song);

        // Remove the oldest song if capacity is exceeded
        if (songList.size() > capacity) {
            songList.removeFirst();
        }
    }

    public List<Song> getRecentlyPlayed(User user) {
        return userSongMap.getOrDefault(user, new LinkedList<>());
    }
}
