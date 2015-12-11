package de.atomfrede.github.karaoke.server.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jongo.marshall.jackson.oid.MongoId;

import java.util.Date;

public class Triple {

    @MongoId
    @JsonProperty
    private String _id;

    @JsonProperty
    private Singer left;

    @JsonProperty
    private Singer right;

    @JsonProperty
    private Song songToSing;

    @JsonProperty
    private Date created_at;

    public Triple(){
        this.created_at = new Date();
    }

    public Triple(Singer l, Singer r, Song s){
        this();
        left = l;
        right = r;
        songToSing = s;
    }

    public Triple(Singer l, Singer r, Song s, String id){
        this(l,r,s);
        this._id = id;
    }

    public String id() {
        return _id;
    }
    public Singer left(){return left;}
    public Singer right(){return right;}
    public Song songToSing(){return songToSing;}
}
