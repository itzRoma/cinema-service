package com.itzroma.mate.cinemaservice.dao.impl;

import com.itzroma.mate.cinemaservice.dao.AbstractCrudDao;
import com.itzroma.mate.cinemaservice.dao.UserDao;
import com.itzroma.mate.cinemaservice.exception.DataProcessingException;
import com.itzroma.mate.cinemaservice.model.User;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserCrudDaoImpl extends AbstractCrudDao<User> implements UserDao {
    public UserCrudDaoImpl(SessionFactory factory) {
        super(factory, User.class);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM User u "
                            + "INNER JOIN FETCH u.roles "
                            + "WHERE u.email = :emailParam", User.class)
                    .setParameter("emailParam", email)
                    .uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get user with email " + email, e);
        }
    }

    @Override
    public Optional<User> get(Long id) {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM User u "
                            + "INNER JOIN FETCH u.roles "
                            + "WHERE u.id = :idParam", User.class)
                    .setParameter("idParam", id)
                    .uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get user with id " + id, e);
        }
    }

    @Override
    public List<User> getAll() {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM User u "
                            + "INNER JOIN FETCH u.roles", User.class)
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all users", e);
        }
    }
}
