package com.itzroma.mate.cinemaservice.controller;

import com.itzroma.mate.cinemaservice.dto.request.MovieSessionRequestDto;
import com.itzroma.mate.cinemaservice.dto.response.MovieSessionResponseDto;
import com.itzroma.mate.cinemaservice.model.MovieSession;
import com.itzroma.mate.cinemaservice.service.MovieSessionService;
import com.itzroma.mate.cinemaservice.service.mapper.RequestDtoMapper;
import com.itzroma.mate.cinemaservice.service.mapper.ResponseDtoMapper;
import com.itzroma.mate.cinemaservice.util.DateTimePatternUtil;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final RequestDtoMapper<MovieSessionRequestDto, MovieSession>
            movieSessionRequestDtoMapper;
    private final ResponseDtoMapper<MovieSessionResponseDto, MovieSession>
            movieSessionResponseDtoMapper;

    public MovieSessionController(
            MovieSessionService movieSessionService,
            RequestDtoMapper<MovieSessionRequestDto, MovieSession> movieSessionRequestDtoMapper,
            ResponseDtoMapper<MovieSessionResponseDto, MovieSession> movieSessionResponseDtoMapper
    ) {
        this.movieSessionService = movieSessionService;
        this.movieSessionRequestDtoMapper = movieSessionRequestDtoMapper;
        this.movieSessionResponseDtoMapper = movieSessionResponseDtoMapper;
    }

    @PostMapping
    public MovieSessionResponseDto add(@RequestBody @Valid MovieSessionRequestDto requestDto) {
        MovieSession movieSession = movieSessionService.add(
                movieSessionRequestDtoMapper.mapToModel(requestDto)
        );
        return movieSessionResponseDtoMapper.mapToDto(movieSession);
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> findAvailableSessions(
            @RequestParam Long movieId,
            @RequestParam @DateTimeFormat(pattern = DateTimePatternUtil.DATE) LocalDate date
    ) {
        return movieSessionService.findAvailableSessions(movieId, date).stream()
                .map(movieSessionResponseDtoMapper::mapToDto)
                .toList();
    }

    @PutMapping("/{id}")
    public MovieSessionResponseDto update(
            @PathVariable Long id,
            @RequestBody @Valid MovieSessionRequestDto requestDto
    ) {
        MovieSession movieSession = movieSessionRequestDtoMapper.mapToModel(requestDto);
        movieSession.setId(id);
        movieSessionService.update(movieSession);
        return movieSessionResponseDtoMapper.mapToDto(movieSession);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        movieSessionService.delete(movieSessionService.get(id));
    }
}
