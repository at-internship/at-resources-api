package com.resources.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.resources.domain.CreateStoryResponse;
import com.resources.dto.StoryDTO;
import com.resources.services.ResourcesService;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiParam;

@RequestMapping(value = "/api/v1")
@RestController
@Slf4j
public class ResourcesController {
	
	@Autowired
	private ResourcesService resourcesService;

	@GetMapping(value="/story", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public List<StoryDTO> getAllStories(){
		log.info("info log");
		log.warn("warn log ");
		log.error("error log");
		log.debug("debug log");
		return resourcesService.getStories();
	}

	@PostMapping(value = "/story")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CreateStoryResponse> postStories(
			@ApiParam(value = "Post story request", required = true) @RequestBody StoryDTO request) {
		CreateStoryResponse response = resourcesService.createStory(request);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping (value = "/story/{id}", produces = "application/json")
	@ResponseStatus (value = HttpStatus.OK )
	public StoryDTO updateStory(@RequestBody StoryDTO request, @PathVariable String id){
		return resourcesService.updateStory(request, id);
	}
}
