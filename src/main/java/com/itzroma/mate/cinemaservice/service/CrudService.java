package com.itzroma.mate.cinemaservice.service;

import java.util.List;

public interface CrudService<T> {
    T add(T entity);

    T get(Long id);

    List<T> getAll();

    T update(T entity);

    void delete(T entity);
}
