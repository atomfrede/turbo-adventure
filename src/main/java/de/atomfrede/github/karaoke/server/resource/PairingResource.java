package de.atomfrede.github.karaoke.server.resource;

import de.atomfrede.github.karaoke.server.entity.*;
import com.codahale.metrics.annotation.Timed;
import de.atomfrede.github.karaoke.server.mongo.PairingRepository;
import de.atomfrede.github.karaoke.server.mongo.SingerRepository;
import de.atomfrede.github.karaoke.server.mongo.SongRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/pairing")
@Produces(MediaType.APPLICATION_JSON)
public class PairingResource {

    private final SingerRepository singerRepository;
    private final SongRepository songRepository;
    private final PairingRepository pairingRepository;

    public PairingResource(final SingerRepository singerRepository, final SongRepository songRepository, final PairingRepository pairingRepository) {
        this.singerRepository = singerRepository;
        this.songRepository = songRepository;
        this.pairingRepository = pairingRepository;
    }

    @GET
    @Timed
    public  Pairings getPairingHistory(){
        Iterable<Triple> history = pairingRepository.getHistory();
        return new Pairings(history);
    }

    @POST
    @Timed
    public Triple generatePairing() {
        Song song = this.songRepository.getRandom();
        Singer[] singers = this.singerRepository.getRandomPair(song.femaleVoice(), song.maleVoice());

        if(singers == null || song == null){
            return null;
        }

        Triple t = new Triple(singers[0], singers[1], song);
        this.pairingRepository.save(t);
        return t;
    }

}
