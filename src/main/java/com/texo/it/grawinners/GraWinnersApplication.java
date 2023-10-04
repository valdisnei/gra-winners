package com.texo.it.grawinners;

import com.texo.it.grawinners.application.domain.movie.model.MovieDTO;
import com.texo.it.grawinners.application.domain.movie.usecase.MovieUseCase;
import com.texo.it.grawinners.application.domain.producer.usecase.ProducerUseCase;
import com.texo.it.grawinners.infrastructure.repository.MovieProducerRepository;
import com.texo.it.grawinners.infrastructure.repository.entity.Movie;
import com.texo.it.grawinners.infrastructure.repository.entity.MovieProducer;
import com.texo.it.grawinners.infrastructure.repository.entity.Producer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

import static java.util.Arrays.stream;

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
                    stream(row.getProducers().split(",|\\ and "))
                            .toList().stream()
                            .filter(Strings::isNotBlank)
                            .forEach(producer -> {
                                row.setProducers(producer);
                                Producer savedProducer = producerUseCase.saveProducers(row, producer);

                                movieProducerRepository.save(new MovieProducer(movie, savedProducer));
                            });
                });
        log.info("Insertions completed!");
    }


}
