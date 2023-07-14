package com.itzroma.mate.cinemaservice.dao;

import com.itzroma.mate.cinemaservice.exception.DataProcessingException;
import com.itzroma.mate.cinemaservice.util.StringUtils;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractCrudDao<T> implements CrudDao<T> {
    protected final SessionFactory factory;
    private final Class<T> clazz;

    protected AbstractCrudDao(SessionFactory factory, Class<T> clazz) {
        this.factory = factory;
        this.clazz = clazz;
    }

    @Override
    public T add(T entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException(String.format("Can't add %s %s",
                    StringUtils.camelCaseToRegularText(clazz.getSimpleName()), entity
            ), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<T> get(Long id) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.get(clazz, id));
        } catch (Exception e) {
            throw new DataProcessingException(String.format("Can't get %s by id %d",
                    StringUtils.camelCaseToRegularText(clazz.getSimpleName()), id
            ), e);
        }
    }

    @Override
    public List<T> getAll() {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM " + clazz.getSimpleName(), clazz)
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException(String.format("Can't get all %s",
                    StringUtils.pluralOf(StringUtils.camelCaseToRegularText(clazz.getSimpleName()))
            ), e);
        }
    }

    @Override
    public T update(T entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException(String.format("Can't update %s %s",
                    StringUtils.camelCaseToRegularText(clazz.getSimpleName()), entity
            ), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delele(T entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException(String.format("Can't delete %s %s",
                    StringUtils.camelCaseToRegularText(clazz.getSimpleName()), entity
            ), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
