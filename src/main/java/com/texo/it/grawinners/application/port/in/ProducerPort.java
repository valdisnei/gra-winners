package com.texo.it.grawinners.application.port.in;

import com.texo.it.grawinners.application.domain.movie.model.MovieDTO;
import com.texo.it.grawinners.infrastructure.repository.entity.MovieProducer;
import com.texo.it.grawinners.infrastructure.repository.entity.Producer;

import java.util.List;

public interface ProducerPort {
    Producer save(MovieDTO movie, String producer);

    List<MovieProducer> findByWinner(Boolean isWinner);
}
