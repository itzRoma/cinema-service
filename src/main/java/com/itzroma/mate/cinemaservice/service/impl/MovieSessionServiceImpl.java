package com.itzroma.mate.cinemaservice.service.impl;

import com.itzroma.mate.cinemaservice.dao.MovieSessionDao;
import com.itzroma.mate.cinemaservice.model.MovieSession;
import com.itzroma.mate.cinemaservice.service.AbstractCrudService;
import com.itzroma.mate.cinemaservice.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MovieSessionServiceImpl
        extends AbstractCrudService<MovieSession>
        implements MovieSessionService {
    private final MovieSessionDao movieSessionDao;

    public MovieSessionServiceImpl(MovieSessionDao movieSessionDao) {
        super(movieSessionDao, MovieSession.class);
        this.movieSessionDao = movieSessionDao;
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return movieSessionDao.findAvailableSessions(movieId, date);
    }
}
