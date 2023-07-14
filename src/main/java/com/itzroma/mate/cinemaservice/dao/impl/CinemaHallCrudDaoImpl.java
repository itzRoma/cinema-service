package com.itzroma.mate.cinemaservice.dao.impl;

import com.itzroma.mate.cinemaservice.dao.AbstractCrudDao;
import com.itzroma.mate.cinemaservice.dao.CinemaHallDao;
import com.itzroma.mate.cinemaservice.model.CinemaHall;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallCrudDaoImpl extends AbstractCrudDao<CinemaHall> implements CinemaHallDao {
    public CinemaHallCrudDaoImpl(SessionFactory factory) {
        super(factory, CinemaHall.class);
    }
}
