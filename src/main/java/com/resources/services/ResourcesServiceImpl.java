package com.resources.services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resources.domain.CreateStoryResponse;
import com.resources.model.Story;
import com.resources.repository.StoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ResourcesServiceImpl implements ResourcesService {

	@Autowired
	private StoryRepository storyRepository;

	@Override
	public CreateStoryResponse createStory(Story story) {
		CreateStoryResponse response = new CreateStoryResponse();
		story.setSprint_id(new ObjectId());
		story.setUser_id(new ObjectId());
		response.setId(storyRepository.save(story).get_id());
		log.info("Story saved with id: {}", response.getId());
		return response;
	}

	@Override
	public List<Story> getStories() {
		
		List<Story> response=storyRepository.findAll();
		log.info("Consulted sucessfully on mongoDB");
		return response;
	}
}
