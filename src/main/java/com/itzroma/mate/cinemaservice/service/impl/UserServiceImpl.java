package com.itzroma.mate.cinemaservice.service.impl;

import com.itzroma.mate.cinemaservice.dao.UserDao;
import com.itzroma.mate.cinemaservice.model.User;
import com.itzroma.mate.cinemaservice.service.AbstractCrudService;
import com.itzroma.mate.cinemaservice.service.UserService;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractCrudService<User> implements UserService {
    private final PasswordEncoder encoder;
    private final UserDao userDao;

    public UserServiceImpl(PasswordEncoder encoder, UserDao userDao) {
        super(userDao, User.class);
        this.encoder = encoder;
        this.userDao = userDao;
    }

    @Override
    public User add(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
