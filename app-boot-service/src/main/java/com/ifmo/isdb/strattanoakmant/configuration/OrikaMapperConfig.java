package com.ifmo.isdb.strattanoakmant.configuration;

import com.ifmo.isdb.strattanoakmant.controller.dto.LoginDto;
import com.ifmo.isdb.strattanoakmant.model.Login;
import dev.akkinoc.spring.boot.orika.OrikaMapperFactoryConfigurer;
import ma.glasnost.orika.MapperFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class OrikaMapperConfig implements OrikaMapperFactoryConfigurer {
    @Override
    public void configure(@NotNull MapperFactory orikaMapperFactory) {
        orikaMapperFactory.classMap(Login.class, LoginDto.class).byDefault().register();
    }
}
