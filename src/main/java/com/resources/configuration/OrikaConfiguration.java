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
import java.util.Map;

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
                .fieldMap("id", "id").converter("idConverter").add()
                .fieldMap("sprint_id", "sprintId").converter("idConverter").add()
                .fieldMap("user_id", "userId").converter("idConverter").add()
                .fieldMap("acceptance_criteria", "acceptanceCriteria").add()
                .fieldMap("story_points", "storyPoints").add()
                .fieldMap("start_date","startDate").add()
                .fieldMap("due_date","dueDate").add()
                .fieldMap("create_date","createDate").add()
                .byDefault().register();
        mapperFactory.classMap(StoryRequestDTO.class, Story.class)
                .fieldMap("priority", "priority").converter("priority").add()
                .fieldMap("sprintId", "sprint_id").converter("idConverter").add()
                .fieldMap("userId", "user_id").converter("idConverter").add()
                .fieldMap("acceptanceCriteria", "acceptance_criteria").add()
                .fieldMap("storyPoints", "story_points").add()
                .fieldMap("startDate","start_date").add()
                .fieldMap("dueDate","due_date").add()
                .fieldMap("createDate","create_date").add()
                .byDefault().register();

        return mapperFactory;
    }

    public class DateConverter extends BidirectionalConverter<Date,String> {

        @Override
        public String convertTo(Date date, Type<String> type, MappingContext mappingContext) {
            return new SimpleDateFormat("yyyy-MM-dd").format(date);
        }

        @SneakyThrows
        @Override
        public Date convertFrom(String s, Type<Date> type, MappingContext mappingContext) {
            return new SimpleDateFormat("yyyy-MM-dd").parse(s);
        }
    }


    public class PriorityConverter extends BidirectionalConverter<String, Integer> {

        @Override
        public Integer convertTo(String s, Type<Integer> type, MappingContext mappingContext) {
            return PriorityConstants.valueOf(s).getOrd();
        }

        @Override
        public String convertFrom(Integer integer, Type<String> type, MappingContext mappingContext) {
            return PriorityConstants.getPriority(integer);
        }
    }

    public class IdConverter extends BidirectionalConverter<String, ObjectId> {

        @Override
        public ObjectId convertTo(String s, Type<ObjectId> type, MappingContext mappingContext) {
            return new ObjectId();
        }

        @Override
        public String convertFrom(ObjectId objectId, Type<String> type, MappingContext mappingContext) {
            return objectId.toString();
        }
    }

}