package de.atomfrede.github.karaoke.server.mongo;

import com.mongodb.DB;
import de.atomfrede.github.karaoke.server.entity.Song;
import de.atomfrede.github.karaoke.server.repository.CrudRepository;
import org.jongo.MongoCollection;

public class SongRepository extends JongoManaged implements CrudRepository<Song, String> {

    final String COLLECTION_NAME = "songs";
    final String ID_QUERY = "{_id:#}";
    final String IDS_QUERY = "{_id:{$in:#}}";

    MongoCollection collection;

    public SongRepository(DB db) {
        super(db);
        collection = jongo.getCollection(COLLECTION_NAME);
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(String s) {

    }

    @Override
    public void delete(Iterable<? extends Song> entities) {

    }

    @Override
    public void delete(Song entity) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public boolean exists(String s) {
        return false;
    }

    @Override
    public Iterable<Song> findAll() {
        return null;
    }

    @Override
    public Iterable<Song> findAll(Iterable<String> strings) {
        return null;
    }

    @Override
    public Song findOne(String s) {
        return null;
    }

    @Override
    public <S extends Song> Iterable<S> save(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends Song> S save(S entity) {
        return null;
    }
}
