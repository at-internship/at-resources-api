package com.resources.configuration;

import com.resources.model.Sprint;
import com.resources.model.json;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Configuration
public class OrikaConfiguration extends ConfigurableMapper {

    @Bean
    public MapperFactory mapperFactoryClass(){

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Sprint.class, json.class).byDefault().register();
        mapperFactory.classMap(json.class, Sprint.class).byDefault().register();

        return mapperFactory;
    }


}