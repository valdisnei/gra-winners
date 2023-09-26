package com.texo.it.grawinners.infrastructure.controller;


import com.texo.it.grawinners.application.domain.minmax.model.MinMax;
import com.texo.it.grawinners.application.domain.minmax.usecase.ProducerMinMaxUseCase;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@AllArgsConstructor
@Api(tags = "MinMax", description = "MinMax's list")
@RestController
@RequestMapping("api/v1/producers")
public class MinMaxController {

    private final ProducerMinMaxUseCase producerMinMaxUseCase;

    @ApiOperation(value = "Entities MinMax", nickname = "getMaxAndMinPrizes",
            notes = "Obter o produtor com maior intervalo entre dois prêmios consecutivos, e o que\n" +
                    "obteve dois prêmios mais rápido",
            response = MinMax.class, tags = {"MinMax"})
    @ApiResponses({ @ApiResponse(code = 200, message = "MinMax successfully executed"),
            @ApiResponse(code = 404, message = "The request resource was not found") })
    @ResponseStatus(OK)
    @GetMapping("intervals")
    public ResponseEntity<MinMax> getMaxAndMinPrizes() {
        log.info("searching min and max winners...");

        return ResponseEntity.ok(producerMinMaxUseCase.buildMinMax());
    }

}
