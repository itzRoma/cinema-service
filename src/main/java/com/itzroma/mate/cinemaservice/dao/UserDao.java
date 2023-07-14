package com.itzroma.mate.cinemaservice.dao;

import com.itzroma.mate.cinemaservice.model.User;
import java.util.Optional;

public interface UserDao extends CrudDao<User> {
    Optional<User> findByEmail(String email);
}
