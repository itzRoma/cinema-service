package com.itzroma.mate.cinemaservice.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itzroma.mate.cinemaservice.util.DateTimePatternUtil;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class MovieSessionRequestDto {
    @Positive
    private Long movieId;
    @Positive
    private Long cinemaHallId;
    @NotNull
    private LocalDateTime showTime;

    public Long getMovieId() {
        return movieId;
    }

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = DateTimePatternUtil.DATE_TIME_TO_MINUTES)
    public LocalDateTime getShowTime() {
        return showTime;
    }
}
