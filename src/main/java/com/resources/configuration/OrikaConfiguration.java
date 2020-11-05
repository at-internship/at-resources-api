package com.resources.configuration;

import com.resources.mapper.OrikaMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrikaConfiguration {
    private MapperFacade mapperFacade;

    @Bean
    public MapperFactory mapperFactoryClass(MapperFactory mapperFactory){
        this.mapperFacade = mapperFactory.getMapperFacade();

        ConverterFactory converterFactory = mapperFactory.getConverterFactory();
        converterFactory.registerConverter(new OrikaMapper.DateConverter());
        converterFactory.registerConverter( "priority", new OrikaMapper.PriorityConverter());
        converterFactory.registerConverter("idConverter", new OrikaMapper.IdConverter());

        return mapperFactory;
    }

}
