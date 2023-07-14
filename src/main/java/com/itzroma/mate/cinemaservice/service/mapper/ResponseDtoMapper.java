package com.itzroma.mate.cinemaservice.service.mapper;

public interface ResponseDtoMapper<D, T> {
    D mapToDto(T entity);
}
