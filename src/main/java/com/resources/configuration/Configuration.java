package com.resources.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.context.annotation.Bean;

public class Configuration {
	
	@Autowired
	private MongoDatabaseFactory mongoDatabaseFactory;

	@Autowired
	private MongoMappingContext mongoMappingContext;
	@Bean
	public MappingMongoConverter mappingMongoConverter() {
	DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDatabaseFactory);
	MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
	converter.setTypeMapper(new DefaultMongoTypeMapper(null));
	return converter;
	}

}
