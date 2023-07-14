package com.itzroma.mate.cinemaservice.security;

import com.itzroma.mate.cinemaservice.model.Role;
import com.itzroma.mate.cinemaservice.model.User;
import com.itzroma.mate.cinemaservice.service.RoleService;
import com.itzroma.mate.cinemaservice.service.ShoppingCartService;
import com.itzroma.mate.cinemaservice.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final RoleService roleService;
    private final ShoppingCartService shoppingCartService;

    public AuthenticationServiceImpl(
            UserService userService,
            RoleService roleService,
            ShoppingCartService shoppingCartService
    ) {
        this.userService = userService;
        this.roleService = roleService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public User register(String email, String password) {
        User user = new User(email, password);
        user.addRole(roleService.getByName(Role.RoleName.USER));
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
