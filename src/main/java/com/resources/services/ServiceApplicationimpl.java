package com.resources.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import com.resources.model.Story_id;
import com.resources.AtResourcesApi2Application;
import com.resources.errorhandling.HttpExceptionMessage;
import com.resources.errorhandling.PathErrorMessage;
import com.resources.exception.NotFoundException;
import com.resources.model.Story;
import com.resources.repository.RepositoryApplication;

import java.util.List;

@Service
@Slf4j
public class ServiceApplicationimpl implements ServiceApplication {

	@Autowired
	RepositoryApplication repositoryApplication;
	
	@Override
	public Story_id createStory(Story story) {
		String userIdDb = repositoryApplication.save(story).get_id();
		log.info("Created sucessfully on mongoDB");
		Story_id userId = new Story_id();
		userId.setId(userIdDb);
		return userId;
	}
	
	@Override
	public List<Story> getStories() {
		List<Story> response=repositoryApplication.findAll();
		log.info("Consulted sucessfully on mongoDB");
		return response;
	}

	@Override
	public List<Story> getStoriesBy_id(String id) {
		List<Story> users = repositoryApplication.findStoriesBy_id(id);
		return users;
	}
	
	@Override
	public void deleteStory(String id) {
		if (repositoryApplication.existsById(id)) {
			AtResourcesApi2Application.logger.info("Deleting story...");
			repositoryApplication.deleteById(id);
		} else {
			AtResourcesApi2Application.logger.error("Tried to delete story but couldnt find an ID.");
			throw new NotFoundException("noooooooooooooooo", PathErrorMessage.pathApi,HttpStatus.NOT_FOUND);
		}
	}

}// End class
