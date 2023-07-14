package com.itzroma.mate.cinemaservice.service.mapper;

import com.itzroma.mate.cinemaservice.dto.response.ShoppingCartResponseDto;
import com.itzroma.mate.cinemaservice.model.ShoppingCart;
import com.itzroma.mate.cinemaservice.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper implements
        ResponseDtoMapper<ShoppingCartResponseDto, ShoppingCart> {

    @Override
    public ShoppingCartResponseDto mapToDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto responseDto = new ShoppingCartResponseDto();
        responseDto.setUserId(shoppingCart.getUser().getId());
        responseDto.setTicketIds(
                shoppingCart.getTickets().stream()
                        .map(Ticket::getId)
                        .toList()
        );
        return responseDto;
    }
}
