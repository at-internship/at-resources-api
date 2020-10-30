package com.resources.services;

import com.resources.model.Story_id;
import com.resources.model.Story;

import java.util.List;

public interface ServiceApplication {

	public Story_id createStory(Story story);
	public List<Story> getStories();
	public List<Story> getStoriesBy_id(String id) ;
	void deleteStory(String id);
}
