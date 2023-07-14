package com.itzroma.mate.cinemaservice.service.impl;

import com.itzroma.mate.cinemaservice.dao.MovieDao;
import com.itzroma.mate.cinemaservice.model.Movie;
import com.itzroma.mate.cinemaservice.service.AbstractCrudService;
import com.itzroma.mate.cinemaservice.service.MovieService;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl extends AbstractCrudService<Movie> implements MovieService {
    public MovieServiceImpl(MovieDao movieDao) {
        super(movieDao, Movie.class);
    }
}
