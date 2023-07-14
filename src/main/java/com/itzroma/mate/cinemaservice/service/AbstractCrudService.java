package com.itzroma.mate.cinemaservice.service;

import com.itzroma.mate.cinemaservice.dao.CrudDao;
import com.itzroma.mate.cinemaservice.util.StringUtils;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class AbstractCrudService<T> implements CrudService<T> {
    protected final CrudDao<T> crudDao;
    private final Class<T> clazz;

    protected AbstractCrudService(CrudDao<T> crudDao, Class<T> clazz) {
        this.crudDao = crudDao;
        this.clazz = clazz;
    }

    @Override
    public T add(T entity) {
        return crudDao.add(entity);
    }

    @Override
    public T get(Long id) {
        return crudDao.get(id).orElseThrow(
                () -> new NoSuchElementException(String.format("Can't get %s by id %d",
                        StringUtils.camelCaseToRegularText(clazz.getSimpleName()), id))
        );
    }

    @Override
    public List<T> getAll() {
        return crudDao.getAll();
    }

    @Override
    public T update(T entity) {
        return crudDao.update(entity);
    }

    @Override
    public void delete(T entity) {
        crudDao.delele(entity);
    }
}
