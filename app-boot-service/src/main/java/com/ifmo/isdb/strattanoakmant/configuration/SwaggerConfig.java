package com.ifmo.isdb.strattanoakmant.configuration;

import com.ifmo.isdb.strattanoakmant.Application;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;

import static springfox.documentation.builders.PathSelectors.any;

@Configuration
public class SwaggerConfig  {

    private static final Logger log =
            LoggerFactory.getLogger(SwaggerConfig.class);

    @Bean
    public Docket api() {
        log.debug("Creating swagger api...");
        return new Docket(DocumentationType.SWAGGER_2)
                .protocols(new HashSet<>(Arrays.asList("http", "https")))
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(any())
                .build();
    }
}
