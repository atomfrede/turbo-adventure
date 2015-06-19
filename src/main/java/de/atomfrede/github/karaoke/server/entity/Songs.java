package de.atomfrede.github.karaoke.server.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Songs {

    @JsonProperty
    private Iterable<Song> songs;

    public Songs() {
    }

    public Songs(Iterable<Song> songs) {
        this.songs = songs;
    }

    public Iterable<Song> getSongs() {
        return songs;
    }

    public void setSongs(Iterable<Song> songs) {
        this.songs = songs;
    }
}
