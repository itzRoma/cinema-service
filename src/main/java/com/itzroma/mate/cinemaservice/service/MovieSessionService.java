package com.itzroma.mate.cinemaservice.service;

import com.itzroma.mate.cinemaservice.model.MovieSession;
import java.time.LocalDate;
import java.util.List;

public interface MovieSessionService extends CrudService<MovieSession> {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);
}
