package com.resources.services;

import java.util.List;
import com.resources.domain.CreateStoryRequest;
import com.resources.domain.CreateStoryResponse;
import com.resources.model.Story;
import javassist.NotFoundException;

public interface ResourcesService {

	CreateStoryResponse createStory(Story story);
	
	public List<Story> getStories();
	
	Story updateStory(CreateStoryRequest request, String id) throws NotFoundException;
}
