package com.resources.service;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import com.resources.model.*;
import lombok.Setter;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ResourcesService {

    public Sprint JSONtoObject(json object) {

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(json.class, Sprint.class).byDefault().register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        Sprint test = mapper.map(object, Sprint.class);
        return test;
    }
    public json ObjectToJSON(Sprint object) {



        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Sprint.class, json.class).byDefault().register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        json test = mapper.map(object, json.class);
        return test;
    }

}
