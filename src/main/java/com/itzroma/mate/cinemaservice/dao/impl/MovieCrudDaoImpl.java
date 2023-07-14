package com.itzroma.mate.cinemaservice.dao.impl;

import com.itzroma.mate.cinemaservice.dao.AbstractCrudDao;
import com.itzroma.mate.cinemaservice.dao.MovieDao;
import com.itzroma.mate.cinemaservice.model.Movie;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class MovieCrudDaoImpl extends AbstractCrudDao<Movie> implements MovieDao {
    public MovieCrudDaoImpl(SessionFactory factory) {
        super(factory, Movie.class);
    }
}
