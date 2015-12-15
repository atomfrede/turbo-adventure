package de.atomfrede.github.karaoke.server.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jongo.marshall.jackson.oid.MongoId;

public class Singer {

    @MongoId
    @JsonProperty
    private String _id;

    @JsonProperty
    private String firstname;
    @JsonProperty
    private String lastname;
    @JsonProperty
    private double random;


    public Singer() {
        this.random = Math.random();
    }

    public Singer(String id) {
        this();
        this._id = id;
    }

    public String id() {
        return _id;
    }

    public String firstname() {
        return this.firstname;
    }

    public String lastname() {
        return this.lastname;
    }

    public Singer setFirstname(final String firstname) {
        this.firstname = firstname;
        return this;
    }

    public Singer setLastname(final String lastname) {
        this.lastname = lastname;
        return this;
    }

}
