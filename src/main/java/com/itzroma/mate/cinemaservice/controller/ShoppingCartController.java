package com.itzroma.mate.cinemaservice.controller;

import com.itzroma.mate.cinemaservice.dto.response.ShoppingCartResponseDto;
import com.itzroma.mate.cinemaservice.model.MovieSession;
import com.itzroma.mate.cinemaservice.model.ShoppingCart;
import com.itzroma.mate.cinemaservice.model.User;
import com.itzroma.mate.cinemaservice.service.MovieSessionService;
import com.itzroma.mate.cinemaservice.service.ShoppingCartService;
import com.itzroma.mate.cinemaservice.service.UserService;
import com.itzroma.mate.cinemaservice.service.mapper.ResponseDtoMapper;
import java.util.NoSuchElementException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final MovieSessionService movieSessionService;
    private final UserService userService;
    private final ResponseDtoMapper<ShoppingCartResponseDto, ShoppingCart>
            shoppingCartResponseDtoMapper;

    public ShoppingCartController(
            ShoppingCartService shoppingCartService,
            UserService userService,
            MovieSessionService movieSessionService,
            ResponseDtoMapper<ShoppingCartResponseDto, ShoppingCart> shoppingCartResponseDtoMapper
    ) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.movieSessionService = movieSessionService;
        this.shoppingCartResponseDtoMapper = shoppingCartResponseDtoMapper;
    }

    @PutMapping("/movie-sessions")
    public void addToCart(Authentication authentication, @RequestParam Long movieSessionId) {
        User user = findUserByAuthentication(authentication);
        MovieSession movieSession = movieSessionService.get(movieSessionId);
        shoppingCartService.addSession(movieSession, user);
    }

    private User findUserByAuthentication(Authentication authentication) {
        return userService.findByEmail(authentication.getName()).orElseThrow(
                () -> new NoSuchElementException(
                        "Can't find user with email " + authentication.getName()
                )
        );
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(Authentication authentication) {
        User user = findUserByAuthentication(authentication);
        return shoppingCartResponseDtoMapper.mapToDto(shoppingCartService.getByUser(user));
    }
}
