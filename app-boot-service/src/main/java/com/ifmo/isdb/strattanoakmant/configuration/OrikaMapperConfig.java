package com.ifmo.isdb.strattanoakmant.configuration;

import com.ifmo.isdb.strattanoakmant.Application;
import com.ifmo.isdb.strattanoakmant.controller.dto.LoginDto;
import com.ifmo.isdb.strattanoakmant.model.Login;
import dev.akkinoc.spring.boot.orika.OrikaMapperFactoryConfigurer;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFactory;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrikaMapperConfig implements OrikaMapperFactoryConfigurer {

    private static final Logger log =
            LoggerFactory.getLogger(OrikaMapperConfig.class);
    @Override
    public void configure(@NotNull MapperFactory orikaMapperFactory) {
        log.debug("Configuring orika...");
        orikaMapperFactory.classMap(Login.class, LoginDto.class).byDefault().register();
    }
}
