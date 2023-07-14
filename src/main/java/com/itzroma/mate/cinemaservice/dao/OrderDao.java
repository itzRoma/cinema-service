package com.itzroma.mate.cinemaservice.dao;

import com.itzroma.mate.cinemaservice.model.Order;
import com.itzroma.mate.cinemaservice.model.User;
import java.util.List;

public interface OrderDao extends CrudDao<Order> {
    List<Order> getOrdersHistory(User user);
}
