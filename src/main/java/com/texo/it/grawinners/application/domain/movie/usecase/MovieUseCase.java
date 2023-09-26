package com.texo.it.grawinners.application.domain.movie.usecase;

import com.texo.it.grawinners.application.domain.movie.model.MovieDTO;
import com.texo.it.grawinners.infrastructure.repository.entity.Movie;

public interface MovieUseCase {
    Movie save(MovieDTO movieDTO);
}
