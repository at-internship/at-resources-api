package com.resources.configuration;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrikaConfiguration {

    @Bean
    public MapperFactory mapperFactoryClass(){

        return new DefaultMapperFactory.Builder().build();
    }


}