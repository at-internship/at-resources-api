package com.resources.configuration;

import com.resources.model.Story;
import com.resources.domain.StoryDTO;
import lombok.SneakyThrows;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.bson.types.ObjectId;

@Component
public class OrikaConfiguration extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory){ mapperFactoryClass(factory);}

    public MapperFactory mapperFactoryClass(MapperFactory mapperFactory){
        ConverterFactory converterFactory = mapperFactory.getConverterFactory();
        converterFactory.registerConverter(new DateConverter());
        converterFactory.registerConverter( "priority", new PriorityConverter());
        converterFactory.registerConverter("idConverter", new IdConverter());

        mapperFactory.classMap(Story.class, StoryDTO.class).byDefault().register();
        mapperFactory.classMap(StoryDTO.class, Story.class)
                .fieldMap("priority", "priority").converter("priority").add()
                .fieldMap("sprint_id", "sprint_id").converter("idConverter").add()
                .fieldMap("user_id", "user_id").converter("idConverter").add()
                .byDefault().register();

        return mapperFactory;
    }

    public class DateConverter extends BidirectionalConverter<Date,String> {

        public String convertTo(Date source, Type<String> destinationType) {

            return new SimpleDateFormat("MM-dd-yyyy").format(source);
        }

        @SneakyThrows
        public Date convertFrom(String source, Type<Date> destinationType) {

            return new SimpleDateFormat("MM-dd-yyyy").parse(source);
        }
    }


    public class PriorityConverter extends CustomConverter<String, Integer> {
        @Override
        public Integer convert(String sourceString, Type<? extends Integer> destinationType) {

            if (sourceString.equalsIgnoreCase("high"))
                return 1;
            else if (sourceString.equalsIgnoreCase("medium"))
                return 2;
            else if (sourceString.equalsIgnoreCase("low"))
                return 3;
            else
                return 0;
        }
    }

    public class IdConverter extends CustomConverter<String, ObjectId> {

        public ObjectId convert(String sourceString, Type<? extends ObjectId> destinationType) {
            return new ObjectId();
        }
    }

}