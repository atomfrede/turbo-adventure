package de.atomfrede.github.karaoke.server.entity;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;

public class SongsTest {

    @Test
    public void assertThatSongAreAdded() {

        Songs songs = new Songs();

        Song killerQueen = new Song().setInterpreter("Queen").setTitle("Killerqueen");
        Song yourSong = new Song().setInterpreter("Elton John").setTitle("Your Song");

        List<Song> listOfSongs = Arrays.asList(killerQueen, yourSong);

        songs.setSongs(listOfSongs);

        assertThat(songs.getSongs(), hasItems(killerQueen, yourSong));
    }
}
