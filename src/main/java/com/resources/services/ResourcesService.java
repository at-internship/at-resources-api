package com.resources.services;

import com.resources.domain.CreateStoryResponse;
import com.resources.dto.StoryDTO;
import java.util.List;

public interface ResourcesService {
	CreateStoryResponse createStory(StoryDTO story);
	public List<StoryDTO> getStories();
	public void deleteStory(String id);
  StoryDTO updateStory(StoryDTO request, String id);
}