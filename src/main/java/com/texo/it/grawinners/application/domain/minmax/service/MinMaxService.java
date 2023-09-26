package com.texo.it.grawinners.application.domain.minmax.service;

import com.texo.it.grawinners.application.domain.minmax.model.MinMax;
import com.texo.it.grawinners.application.domain.minmax.usecase.ProducerMinMaxUseCase;
import com.texo.it.grawinners.application.domain.producer.model.ProducerInterval;
import com.texo.it.grawinners.application.domain.producer.usecase.ProducerUseCase;
import com.texo.it.grawinners.infrastructure.repository.entity.MovieProducer;
import com.texo.it.grawinners.infrastructure.repository.entity.Producer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Math.abs;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.groupingBy;

@Slf4j
@AllArgsConstructor
public class MinMaxService implements ProducerMinMaxUseCase {

    private final ProducerUseCase producerUseCase;

    @Override
    public MinMax buildMinMax() {
        List<MovieProducer> byWinner = producerUseCase.findByWinner(true);

        Map<Producer, List<MovieProducer>> producers = byWinner.stream()
                .filter(w -> w.getProducer().getMovies().size() > 1)
                .collect(groupingBy(MovieProducer::getProducer));

        List<ProducerInterval> max = producers.keySet().stream()
                .map(intervals -> {
                    return getMax(getYears(intervals), intervals.getName());
                })
                .filter(p -> !isNull(p))
                .filter(p -> p.getInterval() > 1)
                .collect(Collectors.toList());

        List<ProducerInterval> min = producers.keySet().stream()
                .map(intervals -> {
                    return getMin(getYears(intervals), intervals.getName());
                })
                .filter(p -> !isNull(p))
                .filter(p -> p.getInterval() == 1)
                .collect(Collectors.toList());

        MinMax result = MinMax.builder()
                .max(max)
                .min(min)
                .build();
        log.info("searching completed! Response: {}", result);
        return result;
    }

    private static ProducerInterval getMax(List<Integer> years, String producerName) {
        if (years.isEmpty() || years.size() == 1) return null;
        return getProducerInterval(years, producerName)
                .max(ProducerInterval::compareTo)
                .get();
    }

    private static ProducerInterval getMin(List<Integer> years, String producerName) {
        if (years.isEmpty() || years.size() == 1) return null;
        return getProducerInterval(years, producerName)
                .min(ProducerInterval::compareTo)
                .get();
    }

    private static List<Integer> getYears(Producer producer) {
        List<MovieProducer> sortedMovieProducers = producer.getMovies().stream()
                .sorted((o1, o2) -> o1.getMovie().getYear().
                        compareTo(o2.getMovie().getYear())).toList();

        List<MovieProducer> producers = sortedMovieProducers.stream()
                .filter(m1 -> m1.getMovie().getWinner())
                .toList();

        return producers.stream()
                .map(m -> m.getMovie().getYear())
                .collect(Collectors.toList());
    }


    private static Stream<ProducerInterval> getProducerInterval(List<Integer> years, String producerName) {
        return IntStream.range(0, years.size() - 1)
                .mapToObj(index -> {
                    return ProducerInterval.builder()
                            .producer(producerName)
                            .interval(abs(years.get(index + 1) - years.get(index)))
                            .previousWin(years.get(index))
                            .followingWin(years.get(index + 1))
                            .build();
                }).toList().stream();
    }
}
