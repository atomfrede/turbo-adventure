package de.atomfrede.github.karaoke.server.repository;

import java.io.Serializable;

public interface CrudRepository<T, ID extends Serializable> {

    long count();

    void delete(ID id);

    void delete(Iterable<? extends T> entities);

    void delete(T entity);

    void deleteAll();

    boolean exists(ID id);

    Iterable<T> findAll();

    Iterable<T> findAll(Iterable<ID> ids);

    T findOne(ID id);

    <S extends T> Iterable<S> save(Iterable<S> entities);

    <S extends T> S save(S entity);

    <S extends T> Iterable<S> update(Iterable<S> entities);

    <S extends T> S update(S entity);
}
