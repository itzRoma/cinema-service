package com.itzroma.mate.cinemaservice.service;

import com.itzroma.mate.cinemaservice.model.User;
import java.util.Optional;

public interface UserService extends CrudService<User> {
    Optional<User> findByEmail(String email);
}
