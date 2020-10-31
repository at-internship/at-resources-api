package com.resources.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.resources.model.Story;

@Repository 
public interface StoryRepository extends MongoRepository<Story,String>{

}
