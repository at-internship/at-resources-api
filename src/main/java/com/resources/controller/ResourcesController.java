 package com.resources.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.resources.domain.CreateStoryRequest;
import com.resources.model.Story;
import com.resources.service.ServiceApplication;



@RestController
public class ResourcesController {
	RestTemplate restTemplate;

	@Autowired
	private ServiceApplication serviceApplication;

	@GetMapping(value="/api/v1/story", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public List<Story> getAllStories(){
		return serviceApplication.getStories();
	}

	@PutMapping (value = "/api/v1/story/{id}", produces = "application/json")
	@ResponseStatus (value = HttpStatus.OK )
	public Story updateStory(@RequestBody CreateStoryRequest request, @PathVariable String id) throws Exception {
		Story resultStory = new Story();
		
		resultStory = serviceApplication.updateStory(request, id);
		
		return resultStory;
	}
	
}