package com.itzroma.mate.cinemaservice.dao.impl;

import com.itzroma.mate.cinemaservice.dao.AbstractCrudDao;
import com.itzroma.mate.cinemaservice.dao.TicketDao;
import com.itzroma.mate.cinemaservice.model.Ticket;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TicketCrudDaoImpl extends AbstractCrudDao<Ticket> implements TicketDao {
    public TicketCrudDaoImpl(SessionFactory factory) {
        super(factory, Ticket.class);
    }
}
