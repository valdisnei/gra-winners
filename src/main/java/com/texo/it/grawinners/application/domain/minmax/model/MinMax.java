package com.texo.it.grawinners.application.domain.minmax.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.texo.it.grawinners.application.domain.producer.model.ProducerInterval;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@JsonPropertyOrder({"min", "max"})
@ToString
@Getter
@Builder
public class MinMax {
	private List<ProducerInterval> min;
	private List<ProducerInterval> max;
}
