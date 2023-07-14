package com.itzroma.mate.cinemaservice.controller;

import com.itzroma.mate.cinemaservice.dto.response.OrderResponseDto;
import com.itzroma.mate.cinemaservice.model.Order;
import com.itzroma.mate.cinemaservice.model.ShoppingCart;
import com.itzroma.mate.cinemaservice.model.User;
import com.itzroma.mate.cinemaservice.service.OrderService;
import com.itzroma.mate.cinemaservice.service.ShoppingCartService;
import com.itzroma.mate.cinemaservice.service.UserService;
import com.itzroma.mate.cinemaservice.service.mapper.ResponseDtoMapper;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final ShoppingCartService shoppingCartService;
    private final OrderService orderService;
    private final UserService userService;
    private final ResponseDtoMapper<OrderResponseDto, Order> orderResponseDtoMapper;

    public OrderController(
            ShoppingCartService shoppingCartService,
            OrderService orderService,
            UserService userService,
            ResponseDtoMapper<OrderResponseDto, Order> orderResponseDtoMapper
    ) {
        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
        this.userService = userService;
        this.orderResponseDtoMapper = orderResponseDtoMapper;
    }

    @PostMapping("/complete")
    public OrderResponseDto completeOrder(Authentication authentication) {
        User user = findUserByAuthentication(authentication);
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        return orderResponseDtoMapper.mapToDto(orderService.completeOrder(shoppingCart));
    }

    private User findUserByAuthentication(Authentication authentication) {
        return userService.findByEmail(authentication.getName()).orElseThrow(
                () -> new NoSuchElementException(
                        "Can't find user with email " + authentication.getName()
                )
        );
    }

    @GetMapping
    public List<OrderResponseDto> getOrderHistory(Authentication authentication) {
        User user = findUserByAuthentication(authentication);
        return orderService.getOrdersHistory(user).stream()
                .map(orderResponseDtoMapper::mapToDto)
                .toList();
    }
}
