package com.itzroma.mate.cinemaservice.security;

import com.itzroma.mate.cinemaservice.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}
