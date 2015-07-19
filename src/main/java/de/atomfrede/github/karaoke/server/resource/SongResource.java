package de.atomfrede.github.karaoke.server.resource;

import com.codahale.metrics.annotation.Timed;
import de.atomfrede.github.karaoke.server.entity.Singers;
import de.atomfrede.github.karaoke.server.entity.Songs;
import de.atomfrede.github.karaoke.server.mongo.SingerRepository;
import de.atomfrede.github.karaoke.server.mongo.SongRepository;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/song")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SongResource {

    private final SongRepository songRepository;

    public SongResource(final SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @GET
    @Timed
    public Songs getSongs() {

        return new Songs(songRepository.findAll());
    }
}
