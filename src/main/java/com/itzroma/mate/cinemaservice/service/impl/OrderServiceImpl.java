package com.itzroma.mate.cinemaservice.service.impl;

import com.itzroma.mate.cinemaservice.dao.OrderDao;
import com.itzroma.mate.cinemaservice.model.Order;
import com.itzroma.mate.cinemaservice.model.ShoppingCart;
import com.itzroma.mate.cinemaservice.model.User;
import com.itzroma.mate.cinemaservice.service.OrderService;
import com.itzroma.mate.cinemaservice.service.ShoppingCartService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final ShoppingCartService shoppingCartService;

    public OrderServiceImpl(OrderDao orderDao, ShoppingCartService shoppingCartService) {
        this.orderDao = orderDao;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        Order order = new Order();
        order.setOrderTime(LocalDateTime.now());
        order.setTickets(shoppingCart.getTickets());
        order.setUser(shoppingCart.getUser());
        orderDao.add(order);
        shoppingCartService.clear(shoppingCart);
        return order;
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        return orderDao.getOrdersHistory(user);
    }
}
