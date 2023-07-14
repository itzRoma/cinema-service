package com.itzroma.mate.cinemaservice.dao.impl;

import com.itzroma.mate.cinemaservice.dao.AbstractCrudDao;
import com.itzroma.mate.cinemaservice.dao.MovieSessionDao;
import com.itzroma.mate.cinemaservice.exception.DataProcessingException;
import com.itzroma.mate.cinemaservice.model.MovieSession;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionCrudDaoImpl
        extends AbstractCrudDao<MovieSession>
        implements MovieSessionDao {
    public MovieSessionCrudDaoImpl(SessionFactory factory) {
        super(factory, MovieSession.class);
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM MovieSession "
                                    + "WHERE movie.id = :movieIdParam "
                                    + "AND DATE_FORMAT(showTime, '%Y-%m-%d') = :dateParam",
                            MovieSession.class)
                    .setParameter("movieIdParam", movieId)
                    .setParameter("dateParam", date.toString())
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException(String.format(
                    "Can't find sessions for movie with id %d at %s", movieId, date
            ), e);
        }
    }
}
