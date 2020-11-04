package com.resources.services;

import java.util.List;

import com.resources.domain.CreateStoryResponse;
import com.resources.model.Story;
import com.resources.domain.StoryDTO;

public interface ResourcesService {
	CreateStoryResponse createStory(StoryDTO storyDTO);
	public List<Story> getStories();
}