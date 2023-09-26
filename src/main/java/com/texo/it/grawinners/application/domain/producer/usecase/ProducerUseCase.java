package com.texo.it.grawinners.application.domain.producer.usecase;

import com.texo.it.grawinners.application.domain.movie.model.MovieDTO;
import com.texo.it.grawinners.infrastructure.repository.entity.MovieProducer;
import com.texo.it.grawinners.infrastructure.repository.entity.Producer;

import java.util.List;

public interface ProducerUseCase {
    Producer saveProducers(MovieDTO movie, String producer);
    List<MovieProducer> findByWinner(Boolean isWinner);
}
