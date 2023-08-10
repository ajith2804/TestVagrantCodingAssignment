
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestRecentlyPlayedStore {

    @Test
    void testRecentlyPlayedSongs() {
        RecentlyPlayedStore store = new RecentlyPlayedStore(3);

        User user1 = new User("user1");
        Song song1 = new Song("S1");
        Song song2 = new Song("S2");
        Song song3 = new Song("S3");
        Song song4 = new Song("S4");

        store.addPlay(user1, song1);
        store.addPlay(user1, song2);
        store.addPlay(user1, song3);

        List<Song> recentlyPlayed = store.getRecentlyPlayed(user1);
        assertEquals(3, recentlyPlayed.size());
        assertEquals("S1", recentlyPlayed.get(0).getName());
        assertEquals("S2", recentlyPlayed.get(1).getName());
        assertEquals("S3", recentlyPlayed.get(2).getName());
        System.out.println("Recently Played for " + user1.getUserId() + ": " + recentlyPlayed);

        store.addPlay(user1, song4);

        recentlyPlayed = store.getRecentlyPlayed(user1);
        assertEquals(3, recentlyPlayed.size());
        assertEquals("S2", recentlyPlayed.get(0).getName());
        assertEquals("S3", recentlyPlayed.get(1).getName());
        assertEquals("S4", recentlyPlayed.get(2).getName());
        System.out.println("Recently Played for " + user1.getUserId() + ": " + recentlyPlayed);
    }

    @Test
    void testNoPlays() {
        RecentlyPlayedStore store = new RecentlyPlayedStore(3);

        User user1 = new User("user1");

        List<Song> recentlyPlayed = store.getRecentlyPlayed(user1);
        assertEquals(0, recentlyPlayed.size());
        System.out.println("Recently Played for " + user1.getUserId() + ": " + recentlyPlayed);
    }

    @Test
    void testMultipleUsers() {
        RecentlyPlayedStore store = new RecentlyPlayedStore(3);

        User user1 = new User("user1");
        User user2 = new User("user2");
        Song song1 = new Song("S1");
        Song song2 = new Song("S2");
        Song song3 = new Song("S3");

        store.addPlay(user1, song1);
        store.addPlay(user2, song2);
        store.addPlay(user1, song3);

        List<Song> recentlyPlayedUser1 = store.getRecentlyPlayed(user1);
        assertEquals(2, recentlyPlayedUser1.size());
        assertEquals("S1", recentlyPlayedUser1.get(0).getName());
        assertEquals("S3", recentlyPlayedUser1.get(1).getName());
        System.out.println("Recently Played for " + user1.getUserId() + ": " + recentlyPlayedUser1);

        List<Song> recentlyPlayedUser2 = store.getRecentlyPlayed(user2);
        assertEquals(1, recentlyPlayedUser2.size());
        assertEquals("S2", recentlyPlayedUser2.get(0).getName());
        System.out.println("Recently Played for " + user2.getUserId() + ": " + recentlyPlayedUser2);
    }

    @Test
    void testCapacityExceeded() {
        RecentlyPlayedStore store = new RecentlyPlayedStore(2);

        User user1 = new User("user1");
        Song song1 = new Song("S1");
        Song song2 = new Song("S2");
        Song song3 = new Song("S3");

        store.addPlay(user1, song1);
        store.addPlay(user1, song2);
        store.addPlay(user1, song3);

        List<Song> recentlyPlayed = store.getRecentlyPlayed(user1);
        assertEquals(2, recentlyPlayed.size());
        assertEquals("S2", recentlyPlayed.get(0).getName());
        assertEquals("S3", recentlyPlayed.get(1).getName());
        System.out.println("Recently Played for " + user1.getUserId() + ": " + recentlyPlayed);
    }

    @Test
    void testCapacityZero() {
        // Test with zero capacity
        RecentlyPlayedStore store = new RecentlyPlayedStore(0);

        User user1 = new User("user1");
        Song song1 = new Song("S1");

        store.addPlay(user1, song1);
        List<Song> recentlyPlayed = store.getRecentlyPlayed(user1);
        assertNotNull(recentlyPlayed);
        assertEquals(0, recentlyPlayed.size());
        System.out.println("Recently Played for " + user1.getUserId() + ": " + recentlyPlayed);
    }
    @Test
    void testNegativeCapacity() {
        // Test with negative capacity
        RecentlyPlayedStore store = new RecentlyPlayedStore(-1);

        User user1 = new User("user1");
        Song song1 = new Song("S1");

        store.addPlay(user1, song1);
        List<Song> recentlyPlayed = store.getRecentlyPlayed(user1);
        assertNotNull(recentlyPlayed);
        assertEquals(0, recentlyPlayed.size());
        System.out.println("Recently Played for " + user1.getUserId() + ": " + recentlyPlayed);
    }

    @Test
    void testNonExistentUser() {
        RecentlyPlayedStore store = new RecentlyPlayedStore(3);

        User user1 = new User("user1");
        Song song1 = new Song("S1");
        Song song2 = new Song("S2");

        store.addPlay(user1, song1);

        // Try to get recently played songs for a user that hasn't played any
        User user2 = new User("user2");
        List<Song> recentlyPlayed = store.getRecentlyPlayed(user2);
        assertNotNull(recentlyPlayed);
        assertEquals(0, recentlyPlayed.size());
        System.out.println("Recently Played for " + user2.getUserId() + ": " + recentlyPlayed);
    }
}
