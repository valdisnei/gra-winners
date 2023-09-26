package com.texo.it.grawinners.application.domain.producer.service;

import com.texo.it.grawinners.application.domain.movie.model.MovieDTO;
import com.texo.it.grawinners.application.port.in.ProducerPort;
import com.texo.it.grawinners.application.domain.producer.usecase.ProducerUseCase;
import com.texo.it.grawinners.infrastructure.repository.entity.MovieProducer;
import com.texo.it.grawinners.infrastructure.repository.entity.Producer;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ProducerService implements ProducerUseCase {

    private final ProducerPort producerPort;

    @Override
    public Producer saveProducers(MovieDTO movie, String producer) {
        return producerPort.save(movie, producer);
    }

    @Override
    public List<MovieProducer> findByWinner(Boolean isWinner) {
        return producerPort.findByWinner(isWinner);
    }
}
