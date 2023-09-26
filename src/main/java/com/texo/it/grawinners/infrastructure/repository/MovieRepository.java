package com.texo.it.grawinners.infrastructure.repository;


import com.texo.it.grawinners.infrastructure.repository.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	List<Movie> findByYear(Integer year);

//	@Query(value="select new com.texoit.thiago.graapi.dto.YearWinnerMovieDTO(movie.year, count(movie.winner)) "
//			+ "from Movie as movie where movie.winner=true group by movie.year having count(movie.winner) > 1")
//	List<YearWinnerMovieDTO> findYearsWithModeThanOneWinner();
	
}
