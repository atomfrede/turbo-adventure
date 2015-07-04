package de.atomfrede.github.karaoke.server.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class KaraokeConfiguration extends Configuration {

    @JsonProperty
    public String mongohost = "localhost";

    @JsonProperty
    public int mongoport = 27017;

    @JsonProperty
    public String mongodb = "karaokeDb";


}
