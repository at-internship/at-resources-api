package com.resources.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.resources.model.Story;
import com.resources.model.Story_id;
import com.resources.services.ServiceApplication;

@Configuration
@RestController
public class ResourcesController {
	
	@Autowired
	private ServiceApplication serviceApplication;

	@GetMapping(value="/api/v1/story/hello", produces = "application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public String getting(){
	return ("Hello at-resources-api"); 
	}
	
	@GetMapping(value="/api/v1/story", produces = "application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public List<Story> getAllStories(){
		return serviceApplication.getStories();
	}
	
	@PostMapping(value = "/api/v1/story", produces = "application/json")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Story_id createUser(@RequestBody Story story) {
		Story_id userId =  new Story_id();
		userId = serviceApplication.createStory(story);
		return userId;		 
	}

	@DeleteMapping(value="/api/v1/story/{id}", produces = "application/json")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void DeleteStory(@PathVariable String id) {
		serviceApplication.deleteStory(id);
		//return ("Story {"+id+"} was deleted."); 
	}
	
}//End class