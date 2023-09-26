package com.texo.it.grawinners;

import com.texo.it.grawinners.application.domain.movie.model.MovieDTO;
import com.texo.it.grawinners.application.domain.movie.usecase.MovieUseCase;
import com.texo.it.grawinners.application.domain.producer.usecase.ProducerUseCase;
import com.texo.it.grawinners.infrastructure.repository.entity.Movie;
import com.texo.it.grawinners.infrastructure.repository.entity.MovieProducer;
import com.texo.it.grawinners.infrastructure.repository.entity.Producer;
import com.texo.it.grawinners.infrastructure.repository.MovieProducerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@Slf4j
@EnableJpaRepositories
@AllArgsConstructor
@SpringBootApplication
public class GraWinnersApplication implements CommandLineRunner {

    private final MovieUseCase movieUseCase;
    private final ProducerUseCase producerUseCase;
    private final MovieProducerRepository movieProducerRepository;

    public static void main(String[] args) {
        SpringApplication.run(GraWinnersApplication.class, args);
    }

    private final List<MovieDTO> dataFromCSV;
    @Override
    public void run(String... args) throws Exception {
        log.info("Inserting csv data into database...");
        dataFromCSV.stream()
                .forEach(row -> {
                    Movie movie = movieUseCase.save(row);
                    Producer producer = producerUseCase.saveProducers(row, row.getProducers());
                    movieProducerRepository.save(new MovieProducer(movie, producer));
                });
        log.info("Insertions completed!");
    }


}
