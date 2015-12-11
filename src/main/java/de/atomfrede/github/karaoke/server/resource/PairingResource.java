package de.atomfrede.github.karaoke.server.resource;

import de.atomfrede.github.karaoke.server.entity.Singer;
import de.atomfrede.github.karaoke.server.entity.Song;
import de.atomfrede.github.karaoke.server.entity.Triple;
import com.codahale.metrics.annotation.Timed;
import de.atomfrede.github.karaoke.server.mongo.SingerRepository;
import de.atomfrede.github.karaoke.server.mongo.SongRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/pairing")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PairingResource {

    private final SingerRepository singerRepository;
    private final SongRepository songRepository;

    public PairingResource(final SingerRepository singerRepository, final SongRepository songRepository) {
        this.singerRepository = singerRepository;
        this.songRepository = songRepository;
    }

    @GET
    @Timed
    public Triple getSingers() {
        Singer[] singers = this.singerRepository.getRandomPair();
        Song song = this.songRepository.getRandom();

        if(singers == null || song == null){
            return null;
        }

        return new Triple(singers[0], singers[1], song);
    }
}
