package com.texo.it.grawinners.application.domain.movie.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;


@JsonPropertyOrder({"year", "title", "studio", "producers", "winner"})
@Data
public class MovieDTO implements Serializable {
    private int year;
    private String title;
    private String studio;
    private String producers;
    private String winner;

}
