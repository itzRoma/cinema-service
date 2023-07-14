package com.itzroma.mate.cinemaservice.security;

import com.itzroma.mate.cinemaservice.model.User;
import com.itzroma.mate.cinemaservice.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("Can't find user with username " + username)
        );
        String[] roles = user.getRoles().stream()
                .map(role -> role.getRoleName().name())
                .toArray(String[]::new);
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }
}
