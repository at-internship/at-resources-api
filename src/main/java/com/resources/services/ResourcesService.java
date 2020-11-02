package com.resources.services;

import com.resources.domain.CreateStoryResponse;
import com.resources.model.Story;

public interface ResourcesService {

	CreateStoryResponse createStory(Story story);
}
