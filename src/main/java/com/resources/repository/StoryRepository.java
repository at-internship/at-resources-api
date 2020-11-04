package com.resources.repository;

import java.util.List;

import com.resources.model.Story;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface StoryRepository extends MongoRepository<Story,String>{
	
	List<Story> findAll();

}