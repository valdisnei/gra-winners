package com.texo.it.grawinners.application.port.in;

import com.texo.it.grawinners.application.domain.movie.model.MovieDTO;
import com.texo.it.grawinners.infrastructure.repository.entity.Movie;

public interface MoviePort {
    Movie save(MovieDTO movieDTO);
}
