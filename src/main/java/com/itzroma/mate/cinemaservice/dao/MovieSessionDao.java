package com.itzroma.mate.cinemaservice.dao;

import com.itzroma.mate.cinemaservice.model.MovieSession;
import java.time.LocalDate;
import java.util.List;

public interface MovieSessionDao extends CrudDao<MovieSession> {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);
}
