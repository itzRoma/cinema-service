package com.itzroma.mate.cinemaservice.controller;

import com.itzroma.mate.cinemaservice.dto.request.UserRequestDto;
import com.itzroma.mate.cinemaservice.dto.response.UserResponseDto;
import com.itzroma.mate.cinemaservice.model.User;
import com.itzroma.mate.cinemaservice.security.AuthenticationService;
import com.itzroma.mate.cinemaservice.service.mapper.ResponseDtoMapper;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final ResponseDtoMapper<UserResponseDto, User> userDtoResponseMapper;

    public AuthenticationController(
            AuthenticationService authenticationService,
            ResponseDtoMapper<UserResponseDto, User> userDtoResponseMapper
    ) {
        this.authenticationService = authenticationService;
        this.userDtoResponseMapper = userDtoResponseMapper;
    }

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody @Valid UserRequestDto requestDto) {
        User user = authenticationService.register(
                requestDto.getEmail(), requestDto.getPassword()
        );
        return userDtoResponseMapper.mapToDto(user);
    }
}
