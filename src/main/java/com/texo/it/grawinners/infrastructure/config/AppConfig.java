package com.texo.it.grawinners.infrastructure.config;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.texo.it.grawinners.application.domain.minmax.service.MinMaxService;
import com.texo.it.grawinners.application.domain.minmax.usecase.ProducerMinMaxUseCase;
import com.texo.it.grawinners.application.domain.movie.model.MovieDTO;
import com.texo.it.grawinners.application.port.in.MoviePort;
import com.texo.it.grawinners.application.domain.movie.service.MovieService;
import com.texo.it.grawinners.application.domain.movie.usecase.MovieUseCase;
import com.texo.it.grawinners.application.port.in.ProducerPort;
import com.texo.it.grawinners.application.domain.producer.service.ProducerService;
import com.texo.it.grawinners.application.domain.producer.usecase.ProducerUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.*;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Slf4j
@Configuration
public class AppConfig {

    @Bean
    public MovieUseCase movieUseCase(MoviePort moviePort){
        return new MovieService(moviePort);
    }

    @Bean
    public ProducerUseCase producerUseCase(ProducerPort producerPort){
        return new ProducerService(producerPort);
    }

    @Bean
    public ProducerMinMaxUseCase producerPrizesUseCase(ProducerUseCase producerUseCase){
        return new MinMaxService(producerUseCase);
    }

    @Bean
    public List<MovieDTO> readCSV() {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = csvMapper
                .typedSchemaFor(MovieDTO.class)
                .withUseHeader(true)
                .withColumnSeparator(',');

        try {
            InputStream inputStream = AppConfig.class.getResourceAsStream("/movielist.csv");

            byte[] bytes = inputStream.readAllBytes();
            log.info("loading csv file...");
            MappingIterator<MovieDTO> complexUsersIter = csvMapper
                    .readerWithTypedSchemaFor(MovieDTO.class)
                    .with(csvSchema)
                    .readValues(bytes);
            return complexUsersIter.readAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(
            WebEndpointsSupplier webEndpointsSupplier,
            ServletEndpointsSupplier servletEndpointsSupplier,
            ControllerEndpointsSupplier controllerEndpointsSupplier,
            EndpointMediaTypes endpointMediaTypes,
            CorsEndpointProperties corsProperties,
            WebEndpointProperties webEndpointProperties,
            Environment environment) {
        List<ExposableEndpoint<?>> allEndpoints = new ArrayList();
        Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
        allEndpoints.addAll(webEndpoints);
        allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
        allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
        String basePath = webEndpointProperties.getBasePath();
        EndpointMapping endpointMapping = new EndpointMapping(basePath);
        boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(
                webEndpointProperties, environment, basePath);
        return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints,
                endpointMediaTypes, corsProperties.toCorsConfiguration(),
                new EndpointLinksResolver(allEndpoints, basePath),
                shouldRegisterLinksMapping, null);
    }

    private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties,
                                               Environment environment, String basePath) {
        return webEndpointProperties.getDiscovery().isEnabled() &&
                (StringUtils.hasText(basePath) ||
                        ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
    }
}
