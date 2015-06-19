package de.atomfrede.github.karaoke.server.entity;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SongTest {

    @Test
    public void shouldBeSet() {

        Song song = new Song()
                .setInterpreter("Queen")
                .setTitle("Killerqueen");

        assertThat(song.interpreter(), is("Queen"));
        assertThat(song.title(), is("Killerqueen"));
    }
}
