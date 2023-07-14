package com.itzroma.mate.cinemaservice.dao;

import com.itzroma.mate.cinemaservice.model.ShoppingCart;
import com.itzroma.mate.cinemaservice.model.User;
import java.util.Optional;

public interface ShoppingCartDao extends CrudDao<ShoppingCart> {
    Optional<ShoppingCart> getByUser(User user);
}
