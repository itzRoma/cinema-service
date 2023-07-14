package com.itzroma.mate.cinemaservice.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<T> {
    T add(T entity);

    Optional<T> get(Long id);

    List<T> getAll();

    T update(T entity);

    void delele(T entity);
}
