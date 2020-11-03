package com.resources.service;


import java.util.List;

import com.resources.domain.CreateStoryRequest;
import com.resources.model.Story;
import javassist.NotFoundException;

public interface ServiceApplication {

	public List<Story> getStories();
	
	Story updateStory(CreateStoryRequest request, String id) throws NotFoundException;

//	Story findById(String id);
}
