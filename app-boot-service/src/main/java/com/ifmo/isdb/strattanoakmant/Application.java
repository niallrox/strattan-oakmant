package com.ifmo.isdb.strattanoakmant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories
@EnableCaching
@EnableSwagger2
public class Application {
    private static final Logger log =
            LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        log.debug("Start strattan oakmant application ");
        SpringApplication.run(Application.class, args);
    }

}
