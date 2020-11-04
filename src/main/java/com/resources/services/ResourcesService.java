package com.resources.services;

import com.resources.domain.CreateStoryResponse;
import com.resources.model.StoryDTO;

public interface ResourcesService {

	CreateStoryResponse createStory(StoryDTO json);
}
