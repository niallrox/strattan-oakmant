package com.ifmo.isdb.strattanoakmant.configuration;

import com.ifmo.isdb.strattanoakmant.Application;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties
public class DatabaseConfig {

    private static final Logger log =
            LoggerFactory.getLogger(DatabaseConfig.class);

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        log.debug("Creating datasource...");
        return DataSourceBuilder.create()
                .build();
    }
}
