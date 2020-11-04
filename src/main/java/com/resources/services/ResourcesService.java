package com.resources.services;

import java.util.List;

import com.resources.domain.CreateStoryResponse;
import com.resources.domain.StoryDTO;
import com.resources.dto.StoryDTO;

public interface ResourcesService {
	CreateStoryResponse createStory(StoryDTO story);
	public List<StoryDTO> getStories();

	CreateStoryResponse createStory(StoryDTO json);
}
