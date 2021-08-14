package com.tkd.spring.reactiverestservice.config;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.callback.BaseCallback;
import org.flywaydb.core.api.callback.Callback;
import org.flywaydb.core.api.callback.Context;
import org.flywaydb.core.api.callback.Event;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FlywayConfig {

    private final FlywayProperties configuration;

    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        return (new FluentConfiguration())
                .locations(configuration.getLocations().toArray(new String[0]))
                .schemas(configuration.getSchemas().toArray(new String[0]))
                .dataSource(configuration.getUrl(), configuration.getUser(), configuration.getPassword())
                .baselineOnMigrate(configuration.isBaselineOnMigrate())
                .outOfOrder(configuration.isOutOfOrder())
                .ignoreMissingMigrations(configuration.isIgnoreMissingMigrations())
                .placeholders(configuration.getPlaceholders())
                .callbacks(new Callback[]{new BaseCallback() {
                    @Override
                    public void handle(Event event, Context context) {
                        // do nothing
                    }
                }}).load();
    }
}
