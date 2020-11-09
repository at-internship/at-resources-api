package com.resources.services;

import com.resources.domain.CreateStoryResponse;
import com.resources.domain.StoryRequestDTO;
import java.util.List;

public interface ResourcesService {
	CreateStoryResponse createStory(StoryRequestDTO story);
	List<StoryRequestDTO> getStories();
	void deleteStory(String id);
  	StoryRequestDTO updateStory(StoryRequestDTO request, String id);
}