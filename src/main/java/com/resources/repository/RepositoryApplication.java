package com.resources.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.resources.model.Story;

@Repository
public interface RepositoryApplication extends MongoRepository<Story, String> {

	List<Story> findAll();
	Story findBy_id();
	@Query("{ '_id' : ?0 }")
	List<Story> findStoriesBy_id(String id);
}
