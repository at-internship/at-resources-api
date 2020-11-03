package com.resources.services;

import java.util.List;

import com.resources.domain.CreateStoryResponse;
import com.resources.model.Story;

public interface ResourcesService {

	CreateStoryResponse createStory(Story story);
	public List<Story> getStories();
}
