package com.itzroma.mate.cinemaservice.service.impl;

import com.itzroma.mate.cinemaservice.dao.ShoppingCartDao;
import com.itzroma.mate.cinemaservice.dao.TicketDao;
import com.itzroma.mate.cinemaservice.model.MovieSession;
import com.itzroma.mate.cinemaservice.model.ShoppingCart;
import com.itzroma.mate.cinemaservice.model.Ticket;
import com.itzroma.mate.cinemaservice.model.User;
import com.itzroma.mate.cinemaservice.service.ShoppingCartService;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartDao shoppingCartDao;
    private final TicketDao ticketDao;

    public ShoppingCartServiceImpl(ShoppingCartDao shoppingCartDao, TicketDao ticketDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.ticketDao = ticketDao;
    }

    @Override
    public void addSession(MovieSession movieSession, User user) {
        Ticket ticket = new Ticket(movieSession, user);
        ticketDao.add(ticket);
        ShoppingCart shoppingCart = getByUser(user);
        shoppingCart.addTicket(ticket);
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return shoppingCartDao.getByUser(user).orElseThrow(
                () -> new NoSuchElementException("Can't get shopping cart for user " + user)
        );
    }

    @Override
    public void registerNewShoppingCart(User user) {
        shoppingCartDao.add(new ShoppingCart(user));
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.clearTickets();
        shoppingCartDao.update(shoppingCart);
    }
}
