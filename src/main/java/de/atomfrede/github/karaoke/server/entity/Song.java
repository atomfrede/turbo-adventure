package de.atomfrede.github.karaoke.server.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jongo.marshall.jackson.oid.MongoId;

public class Song {

    @MongoId
    @JsonProperty
    private String _id;

    @JsonProperty
    private String title;
    @JsonProperty
    private String interpreter;
    @JsonProperty
    private double random;
    @JsonProperty
    private boolean femaleVoice;
    @JsonProperty
    private boolean maleVoice;

    public Song() {
        this.random = Math.random();
    }

    public Song(String id) {
        this();
        this._id = id;
    }

    public String id() {
        return _id;
    }

    public String title() {
        return title;
    }

    public Song setTitle(String title) {
        this.title = title;
        return this;
    }

    public String interpreter() {
        return interpreter;
    }

    public boolean femaleVoice() {
        return femaleVoice;
    }

    public boolean maleVoice() {
        return maleVoice;
    }

    public Song setInterpreter(String interpreter) {
        this.interpreter = interpreter;
        return this;
    }
    public Song setVoices(boolean female, boolean male){
        this.femaleVoice = female;
        this.maleVoice = male;
        return this;
    }
}
