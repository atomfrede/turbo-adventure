package de.atomfrede.github.karaoke.server.resource;

import de.atomfrede.github.karaoke.server.mongo.SingerRepository;
import de.atomfrede.github.karaoke.server.mongo.SongRepository;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
}
