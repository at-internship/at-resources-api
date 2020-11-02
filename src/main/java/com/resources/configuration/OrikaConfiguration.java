package com.resources.configuration;

import com.resources.model.Sprint;
import com.resources.model.json;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.metadata.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Configuration
public class OrikaConfiguration extends ConfigurableMapper {

    @Bean
    public MapperFactory mapperFactoryClass(){
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        ConverterFactory converterFactory = mapperFactory.getConverterFactory();
        converterFactory.registerConverter(new DateToStringConverter());

        mapperFactory.classMap(Sprint.class, json.class).byDefault().register();
        mapperFactory.classMap(json.class, Sprint.class).byDefault().register();

        return mapperFactory;
    }

    @Component
    public class DateToStringConverter extends CustomConverter<Date, String> {

        public String convert(Date sourceDate, Type<? extends String> destinationType) {

            String pattern = "MM-dd-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.format(sourceDate);
        }
    }


}