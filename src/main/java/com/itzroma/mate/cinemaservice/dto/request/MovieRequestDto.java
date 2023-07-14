package com.itzroma.mate.cinemaservice.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class MovieRequestDto {
    @NotBlank
    private String title;
    @Size(max = 200)
    private String description;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
