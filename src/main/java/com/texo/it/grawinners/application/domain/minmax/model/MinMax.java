package com.texo.it.grawinners.application.domain.minmax.model;

import com.texo.it.grawinners.application.domain.producer.model.ProducerInterval;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Builder
public class MinMax {
	private List<ProducerInterval> min;
	private List<ProducerInterval> max;
}
