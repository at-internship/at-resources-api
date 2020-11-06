package com.resources.configuration;

import com.resources.model.Story;
import com.resources.domain.StoryRequestDTO;
import com.resources.services.PriorityConstants;
import lombok.SneakyThrows;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
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

        mapperFactory.classMap(Story.class, StoryRequestDTO.class)
                .fieldMap("priority", "priority").converter("priority").add()
                .fieldMap("sprintId", "sprintId").converter("idConverter").add()
                .fieldMap("userId", "userId").converter("idConverter").add()
                .byDefault().register();
        mapperFactory.classMap(StoryRequestDTO.class, Story.class)
                .fieldMap("priority", "priority").converter("priority").add()
                .fieldMap("sprintId", "sprintId").converter("idConverter").add()
                .fieldMap("userId", "userId").converter("idConverter").add()
                .byDefault().register();

        return mapperFactory;
    }

    public class DateConverter extends BidirectionalConverter<Date,String> {

        @Override
        public String convertTo(Date date, Type<String> type, MappingContext mappingContext) {
            return new SimpleDateFormat("MM-dd-yyyy").format(date);
        }

        @SneakyThrows
        @Override
        public Date convertFrom(String s, Type<Date> type, MappingContext mappingContext) {
            return new SimpleDateFormat("MM-dd-yyyy").parse(s);
        }
    }


    public class PriorityConverter extends BidirectionalConverter<String, Integer> {

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

        @Override
        public Integer convertTo(String s, Type<Integer> type, MappingContext mappingContext) {
            return PriorityConstants.valueOf(s.toUpperCase()).getOrd();
        }

        @Override
        public String convertFrom(Integer integer, Type<String> type, MappingContext mappingContext) {
            if (integer == 1)
                return "HIGH";
            else if (integer == 2)
                return "MEDIUM";
            else if (integer == 3)
                return "LOW";
            else
                return "UNKNOWN";
        }
    }

    public class IdConverter extends CustomConverter<String, ObjectId> {

        public ObjectId convert(String sourceString, Type<? extends ObjectId> destinationType) {
            return new ObjectId();
        }

        @Override
        public ObjectId convert(String s, Type<? extends ObjectId> type, MappingContext mappingContext) {
            return new ObjectId();
        }
    }

}