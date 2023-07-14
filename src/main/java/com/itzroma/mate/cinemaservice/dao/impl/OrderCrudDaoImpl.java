package com.itzroma.mate.cinemaservice.dao.impl;

import com.itzroma.mate.cinemaservice.dao.AbstractCrudDao;
import com.itzroma.mate.cinemaservice.dao.OrderDao;
import com.itzroma.mate.cinemaservice.exception.DataProcessingException;
import com.itzroma.mate.cinemaservice.model.Order;
import com.itzroma.mate.cinemaservice.model.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class OrderCrudDaoImpl extends AbstractCrudDao<Order> implements OrderDao {
    public OrderCrudDaoImpl(SessionFactory factory) {
        super(factory, Order.class);
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        try (Session session = factory.openSession()) {
            return session.createQuery("SELECT DISTINCT o FROM Order o "
                            + "JOIN FETCH o.tickets t "
                            + "JOIN FETCH t.movieSession ms "
                            + "JOIN FETCH ms.cinemaHall "
                            + "JOIN FETCH ms.movie "
                            + "WHERE o.user = :userParam", Order.class)
                    .setParameter("userParam", user)
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get orders history for user " + user, e);
        }
    }
}
