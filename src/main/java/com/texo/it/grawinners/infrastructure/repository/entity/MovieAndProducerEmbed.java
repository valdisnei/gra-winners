package com.texo.it.grawinners.infrastructure.repository.entity;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MovieAndProducerEmbed implements Serializable {
	private Long idMovie;
	
	private Long idProducer;
	
	public MovieAndProducerEmbed() {}
	
	public MovieAndProducerEmbed(Long idMovie, Long idProducer) {
		this.idMovie = idMovie;
		this.idProducer = idProducer;
	}

	public Long getIdMovie() {
		return idMovie;
	}

	public Long getIdProducer() {
		return idProducer;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		
		MovieAndProducerEmbed other = (MovieAndProducerEmbed) obj;
		return Objects.equals(idMovie, other.getIdMovie()) &&
				Objects.equals(idProducer, other.getIdProducer());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idMovie, idProducer);
	}

}
