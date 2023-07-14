package com.itzroma.mate.cinemaservice.service.mapper;

import com.itzroma.mate.cinemaservice.dto.response.OrderResponseDto;
import com.itzroma.mate.cinemaservice.model.Order;
import com.itzroma.mate.cinemaservice.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements ResponseDtoMapper<OrderResponseDto, Order> {
    @Override
    public OrderResponseDto mapToDto(Order order) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setId(order.getId());
        responseDto.setUserId(order.getUser().getId());
        responseDto.setOrderTime(order.getOrderTime());
        responseDto.setTicketIds(
                order.getTickets().stream()
                        .map(Ticket::getId)
                        .toList()
        );
        return responseDto;
    }
}
