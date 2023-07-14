package com.itzroma.mate.cinemaservice.service.mapper;

public interface RequestDtoMapper<D, T> {
    T mapToModel(D dto);
}
