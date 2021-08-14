package com.tkd.spring.reactiverestservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
@EnableR2dbcRepositories
@EnableConfigurationProperties({FlywayProperties.class})
public class ReactiveRestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveRestServiceApplication.class, args);
    }

}
