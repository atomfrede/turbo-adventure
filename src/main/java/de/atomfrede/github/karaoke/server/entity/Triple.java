package de.atomfrede.github.karaoke.server.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
public class Triple {

    @JsonProperty
    private Singer left;

    @JsonProperty
    private Singer right;

    @JsonProperty
    private Song songToSing;

    public Triple(Singer l, Singer r, Song s){
        left = l;
        right = r;
        songToSing = s;
    }

    public Singer left(){return left;}
    public Singer right(){return right;}
    public Song songToSing(){return songToSing;}
}
