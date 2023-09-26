package com.texo.it.grawinners.infrastructure.repository;


import com.texo.it.grawinners.infrastructure.repository.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
	Optional<Producer> findByName(String name);
}
