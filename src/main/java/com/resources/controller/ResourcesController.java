package com.resources.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import com.resources.domain.CreateStoryResponse;
import com.resources.domain.StoryRequestDTO;
import com.resources.services.ResourcesService;

import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiParam;

@RequestMapping(value = "/api/v1")
@Configuration
@RestController
@Slf4j
public class ResourcesController {
	
	private final ResourcesService resourcesService;

	public ResourcesController(ResourcesService resourcesService) {
		this.resourcesService = resourcesService;
	}

	@GetMapping(value="/story", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public List<StoryRequestDTO> getAllStories(){
		log.info("Calling Get Operation");
		return resourcesService.getStories();
	}//END GET
   
	@PostMapping(value = "/story")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CreateStoryResponse> postStories(
			@ApiParam(value = "Post story request", required = true) @RequestBody StoryRequestDTO request) {
		CreateStoryResponse response = resourcesService.createStory(request);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}//END POST
  
  @PutMapping(value = "/story/{id}", produces = "application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public StoryRequestDTO updateStory(@RequestBody StoryRequestDTO request, @PathVariable String id) {
		return resourcesService.updateStory(request, id);
	}//END PUT
  
	@DeleteMapping(value="/story/{id}", produces = "application/json")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void DeleteStory(@PathVariable String id) {
		resourcesService.deleteStory(id);
	}//END DELETE
	
}//END CLASS