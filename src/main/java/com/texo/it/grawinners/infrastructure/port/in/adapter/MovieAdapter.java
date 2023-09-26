package com.texo.it.grawinners.infrastructure.port.in.adapter;

import com.texo.it.grawinners.application.domain.movie.model.MovieDTO;
import com.texo.it.grawinners.application.port.in.MoviePort;
import com.texo.it.grawinners.infrastructure.repository.entity.Movie;
import com.texo.it.grawinners.infrastructure.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MovieAdapter implements MoviePort {

    private final MovieRepository movieRepository;
    @Override
    public Movie save(MovieDTO movieDTO) {
        return movieRepository.save(
                new Movie(movieDTO.getYear(),
                        movieDTO.getTitle(),
                        movieDTO.getWinner()));
    }
}
