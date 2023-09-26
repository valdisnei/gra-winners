package com.texo.it.grawinners.infrastructure.port.in.adapter;

import com.texo.it.grawinners.application.domain.movie.model.MovieDTO;
import com.texo.it.grawinners.application.port.in.ProducerPort;
import com.texo.it.grawinners.infrastructure.repository.entity.MovieProducer;
import com.texo.it.grawinners.infrastructure.repository.entity.Producer;
import com.texo.it.grawinners.infrastructure.repository.MovieProducerRepository;
import com.texo.it.grawinners.infrastructure.repository.ProducerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class ProducerAdapter implements ProducerPort {

    private final ProducerRepository producerRepository;
    private final MovieProducerRepository movieProducerRepository;

    @Override
    public Producer save(MovieDTO movieDTO, String producerName) {
        Optional<Producer> byName = producerRepository.findByName(producerName);

        return byName.map(producerRepository::save)
                .orElseGet(() -> producerRepository.save(new Producer(producerName)));

    }

    @Override
    public List<MovieProducer> findByWinner(Boolean isWinner) {
        return movieProducerRepository.findByMovieWinnerOrderByProducerId(isWinner);
    }
}
