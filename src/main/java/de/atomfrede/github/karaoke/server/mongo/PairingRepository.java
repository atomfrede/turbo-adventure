package de.atomfrede.github.karaoke.server.mongo;

import com.mongodb.DB;
import de.atomfrede.github.karaoke.server.entity.Triple;
import de.atomfrede.github.karaoke.server.repository.CrudRepository;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;

public class PairingRepository extends JongoManaged implements CrudRepository<Triple, String> {

    final String COLLECTION_NAME = "pairing";
    final String ID_QUERY = "{_id:#}";
    final String IDS_QUERY = "{_id:{$in:#}}";

    public PairingRepository(DB db){
        super(db);
        collection = jongo.getCollection(COLLECTION_NAME);
    }

    MongoCollection collection;
    @Override
    public long count() {
        return collection.count();
    }

    @Override
    public void delete(String s) {
        collection.remove(ID_QUERY, new ObjectId(s));
    }

    @Override
    public void delete(Iterable<? extends Triple> entities) {
        entities.forEach(this::delete);
    }

    @Override
    public void delete(Triple entity) {
        collection.remove(ID_QUERY, new ObjectId(entity.id()));
    }

    @Override
    public void deleteAll() {
        collection.drop();
    }

    @Override
    public boolean exists(String s) {
        return findOne(s) != null;
    }

    @Override
    public Iterable<Triple> findAll() {
        return collection.find().as(Triple.class);
    }

    @Override
    public Iterable<Triple> findAll(Iterable<String> strings) {
        return collection.find(IDS_QUERY, strings).as(Triple.class);
    }

    @Override
    public Triple findOne(String s) {
        return collection.findOne(ID_QUERY, new ObjectId(s)).as(Triple.class);
    }

    @Override
    public <T extends Triple> Iterable<T> save(Iterable<T> entities) {
        entities.forEach(this::save);
        return entities;
    }

    @Override
    public <T extends Triple> T save(T entity) {
        collection.save(entity);
        return entity;
    }

    @Override
    public <T extends Triple> Iterable<T> update(Iterable<T> entities) {
        entities.forEach(this::update);
        return entities;
    }

    @Override
    public <T extends Triple> T update(T entity) {
        collection.update(ID_QUERY, new ObjectId(entity.id())).with(entity);
        return entity;
    }

    public Iterable<Triple> getHistory(){
        return collection.find().limit(10).sort("{created_at: -1}").as(Triple.class);
    }
}
