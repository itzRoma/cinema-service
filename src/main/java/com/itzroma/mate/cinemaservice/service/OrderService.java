package com.itzroma.mate.cinemaservice.service;

import com.itzroma.mate.cinemaservice.model.Order;
import com.itzroma.mate.cinemaservice.model.ShoppingCart;
import com.itzroma.mate.cinemaservice.model.User;
import java.util.List;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrdersHistory(User user);
}
