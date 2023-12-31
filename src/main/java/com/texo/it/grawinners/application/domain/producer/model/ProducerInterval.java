package com.texo.it.grawinners.application.domain.producer.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@JsonPropertyOrder({"producer", "interval", "previousWin", "followingWin"})
@ToString
@Getter
@Builder
public class ProducerInterval implements Comparable<ProducerInterval> {
	private String producer;
	private Integer interval;
	private Integer previousWin;
	private Integer followingWin;


	@Override
	public int compareTo(ProducerInterval o) {
		return this.interval - o.interval;
	}
}
