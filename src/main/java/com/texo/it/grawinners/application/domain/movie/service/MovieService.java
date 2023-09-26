package com.texo.it.grawinners.application.domain.movie.service;

import com.texo.it.grawinners.application.domain.movie.model.MovieDTO;
import com.texo.it.grawinners.application.port.in.MoviePort;
import com.texo.it.grawinners.application.domain.movie.usecase.MovieUseCase;
import com.texo.it.grawinners.infrastructure.repository.entity.Movie;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MovieService implements MovieUseCase {

    private final MoviePort moviePort;

    @Override
    public Movie save(MovieDTO movieDTO) {
        return moviePort.save(movieDTO);
    }
}
