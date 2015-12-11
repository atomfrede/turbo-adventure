package de.atomfrede.github.karaoke.server.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pairings {
    @JsonProperty
    private Iterable<Triple> pairings;

    public Pairings() {
    }

    public Pairings(Iterable<Triple> pairings) {
        this.pairings = pairings;
    }

    public Iterable<Triple> getPairings() {
        return pairings;
    }

    public void setPairings(Iterable<Triple> pairings) {
        this.pairings = pairings;
    }
}
