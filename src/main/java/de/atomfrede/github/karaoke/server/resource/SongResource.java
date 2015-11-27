package de.atomfrede.github.karaoke.server.resource;

import com.codahale.metrics.annotation.Timed;
import de.atomfrede.github.karaoke.server.entity.Song;
import de.atomfrede.github.karaoke.server.entity.Songs;
import de.atomfrede.github.karaoke.server.mongo.SongRepository;
import io.dropwizard.jersey.PATCH;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/song")
@Produces(MediaType.APPLICATION_JSON)
public class SongResource {

    private final SongRepository songRepository;

    public SongResource(final SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @GET
    //Send a request with GET HTTP Method
    @Timed
    //die @Timed-Annotation ist, um Performance-Metriken zu erheben
    public Songs getSongs() {

        return new Songs(songRepository.findAll());
    }

    @POST
    //The POST HTTP method is used to add objects to a REST resource
    @Timed
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Songs saveNewSong(@FormParam("title") String title,
                             @FormParam("interpreter") String interpreter) {

        Song newSong = new Song();
        newSong.setTitle(title).setInterpreter(interpreter);

        songRepository.save(newSong);

        return new Songs(songRepository.findAll());
    }

    @PATCH
    @Timed
    @Path("{songId}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Songs updateSong(@PathParam("songId") String songId,
                            @FormParam("title") String title,
                            @FormParam("interpreter") String interpreter) {

        if (songRepository.exists(songId)) {
            Song updateSong = new Song(songId);
            updateSong.setTitle(title).setInterpreter(interpreter);

            songRepository.update(updateSong);
        }

        return new Songs(songRepository.findAll());
    }

    @DELETE
    @Timed
    @Path("{songId}")
    public Songs deleteSong(@PathParam("songId") String songId) {

        songRepository.delete(songId);

        return new Songs(songRepository.findAll());
    }

    @GET
    @Timed
    @Path("{id}")
    public Song getSong(@PathParam("id") final String id) {
        Song song = songRepository.findOne(id);
        if (song != null) {
            return song;
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
}
