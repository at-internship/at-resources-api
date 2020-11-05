package com.resources.configuration;

import com.resources.dto.StoryDTO;
import com.resources.model.Story;
import lombok.SneakyThrows;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.bson.types.ObjectId;

@Component
public class OrikaMapper extends ConfigurableMapper {

    private MapperFacade mapperFacade;
//    @Override
//    protected void configure(MapperFactory factory){ mapperFactoryClass(factory);}

    @Autowired
    public void setMapperFacade(MapperFactory mapperFactory){
        this.mapperFacade = mapperFactory.getMapperFacade();

//        ConverterFactory converterFactory = mapperFactory.getConverterFactory();
//        converterFactory.registerConverter(new DateConverter());
//        converterFactory.registerConverter( "priority", new PriorityConverter());
//        converterFactory.registerConverter("idConverter", new IdConverter());


        mapperFactory.classMap(Story.class, StoryDTO.class).byDefault().register();
        mapperFactory.classMap(StoryDTO.class, Story.class)
//                .fieldMap("priority", "priority").converter("priority").add()
//                .fieldMap("sprintId", "sprint_id").converter("idConverter").add()
//                .fieldMap("userId", "user_id").converter("idConverter").add()
                .byDefault().register();

//        return mapperFactory;
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