package com.itzroma.mate.cinemaservice.service.mapper;

import com.itzroma.mate.cinemaservice.dto.response.UserResponseDto;
import com.itzroma.mate.cinemaservice.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements ResponseDtoMapper<UserResponseDto, User> {
    @Override
    public UserResponseDto mapToDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setEmail(user.getEmail());
        responseDto.setRoles(
                user.getRoles().stream()
                        .map(role -> role.getRoleName().name())
                        .toList()
        );
        return responseDto;
    }
}
