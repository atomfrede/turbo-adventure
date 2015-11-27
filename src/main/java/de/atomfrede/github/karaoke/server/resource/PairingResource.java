package de.atomfrede.github.karaoke.server.resource;

import de.atomfrede.github.karaoke.server.mongo.SingerRepository;
import de.atomfrede.github.karaoke.server.mongo.SongRepository;
import de.atomfrede.github.karaoke.server.service.PairingService;

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
    private final PairingService pairingService;

    public PairingResource(final SingerRepository singerRepository, final SongRepository songRepository, final PairingService pairingService) {
        this.singerRepository = singerRepository;
        this.songRepository = songRepository;
        this.pairingService = pairingService;
    }
}
