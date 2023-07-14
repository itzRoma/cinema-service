package com.itzroma.mate.cinemaservice.service.impl;

import com.itzroma.mate.cinemaservice.dao.CinemaHallDao;
import com.itzroma.mate.cinemaservice.model.CinemaHall;
import com.itzroma.mate.cinemaservice.service.AbstractCrudService;
import com.itzroma.mate.cinemaservice.service.CinemaHallService;
import org.springframework.stereotype.Service;

@Service
public class CinemaHallServiceImpl
        extends AbstractCrudService<CinemaHall>
        implements CinemaHallService {
    public CinemaHallServiceImpl(CinemaHallDao cinemaHallDao) {
        super(cinemaHallDao, CinemaHall.class);
    }
}
