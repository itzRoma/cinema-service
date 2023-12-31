package com.itzroma.mate.cinemaservice.controller;

import com.itzroma.mate.cinemaservice.dto.response.UserResponseDto;
import com.itzroma.mate.cinemaservice.model.User;
import com.itzroma.mate.cinemaservice.service.UserService;
import com.itzroma.mate.cinemaservice.service.mapper.ResponseDtoMapper;
import java.util.NoSuchElementException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ResponseDtoMapper<UserResponseDto, User> userResponseDtoMapper;

    public UserController(
            UserService userService,
            ResponseDtoMapper<UserResponseDto, User> userResponseDtoMapper
    ) {
        this.userService = userService;
        this.userResponseDtoMapper = userResponseDtoMapper;
    }

    @GetMapping("/by-email")
    public UserResponseDto findByEmail(@RequestParam String email) {
        User user = userService.findByEmail(email).orElseThrow(
                () -> new NoSuchElementException("Can't find user with email " + email)
        );
        return userResponseDtoMapper.mapToDto(user);
    }
}
