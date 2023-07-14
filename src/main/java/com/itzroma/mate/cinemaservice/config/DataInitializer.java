package com.itzroma.mate.cinemaservice.config;

import com.itzroma.mate.cinemaservice.model.Role;
import com.itzroma.mate.cinemaservice.model.User;
import com.itzroma.mate.cinemaservice.service.RoleService;
import com.itzroma.mate.cinemaservice.service.UserService;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    private final RoleService roleService;
    private final UserService userService;

    public DataInitializer(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void injectRolesAndAdmin() {
        Role userRole = new Role(Role.RoleName.USER);
        roleService.add(userRole);

        Role adminRole = new Role(Role.RoleName.ADMIN);
        roleService.add(adminRole);

        User user = new User("admin@i.ua", "admin123");
        user.addRole(adminRole);
        userService.add(user);
    }
}
