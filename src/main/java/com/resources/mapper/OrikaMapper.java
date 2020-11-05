package com.resources.mapper;

import com.resources.model.Story;
import com.resources.domain.StoryRequestDTO;
import lombok.SneakyThrows;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.metadata.Type;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class OrikaMapper extends ConfigurableMapper {

    private MapperFacade mapperFacade;

    @Autowired
    public void setMapperFacade(MapperFactory mapperFactory){
        this.mapperFacade = mapperFactory.getMapperFacade();

        ConverterFactory converterFactory = mapperFactory.getConverterFactory();
        converterFactory.registerConverter(new DateConverter());
        converterFactory.registerConverter( "priority", new PriorityConverter());
        converterFactory.registerConverter("idConverter", new IdConverter());


        mapperFactory.classMap(Story.class, StoryRequestDTO.class).byDefault().register();
        mapperFactory.classMap(StoryRequestDTO.class, Story.class)
                .fieldMap("priority", "priority").converter("priority").add()
                .fieldMap("sprintId", "sprint_id").converter("idConverter").add()
                .fieldMap("userId", "user_id").converter("idConverter").add()
                .byDefault().register();
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


    public class PriorityConverter extends CustomConverter<String, Integer> {

        @Override
        public Integer convert(String sourceString, Type<? extends Integer> type, MappingContext mappingContext) {
            if (sourceString.equalsIgnoreCase("high"))
                return 1;
            else if (sourceString.equalsIgnoreCase("medium"))
                return 2;
            else if (sourceString.equalsIgnoreCase("low"))
                return 3;
            else
                return 0;

//            return CategoryConstants.valueOf(sourceString).getOrd()
        }
    }

    public class IdConverter extends CustomConverter<String, ObjectId> {

        @Override
        public ObjectId convert(String s, Type<? extends ObjectId> type, MappingContext mappingContext) {
            return new ObjectId();
        }
    }

}