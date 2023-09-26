package com.texo.it.grawinners.infrastructure.repository;


import com.texo.it.grawinners.infrastructure.repository.entity.MovieProducer;
import com.texo.it.grawinners.infrastructure.repository.entity.MovieAndProducerEmbed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieProducerRepository extends JpaRepository<MovieProducer, MovieAndProducerEmbed> {
	
	@Query(value="select mp from MovieProducer as mp join mp.movie as movie " +
			"join mp.producer as producer "
			+ "where movie.winner = true order by producer.id, movie.year")
	List<MovieProducer> findByMovieWinnerOrderByProducerId(Boolean isWinner);
	
}
