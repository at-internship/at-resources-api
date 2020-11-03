 package com.resources.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.resources.domain.CreateStoryResponse;
import com.resources.model.Story;
import com.resources.services.ResourcesService;
import io.swagger.annotations.ApiParam;
import org.springframework.web.client.RestTemplate;
import com.resources.domain.CreateStoryRequest;

@RequestMapping(value = "/at-resources-api/api/v1/story")
@RestController
public class ResourcesController {
	RestTemplate restTemplate;

	@Autowired
	private ResourcesService resourcesService;


	@GetMapping(value="/get", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public List<Story> getAllStories(){
		return resourcesService.getStories();
	}

	@PostMapping(value = "/post")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CreateStoryResponse> postStories(
			@ApiParam(value = "Post story request", required = true) @RequestBody Story request) {
		CreateStoryResponse response = resourcesService.createStory(request);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping (value = "/put/{id}", produces = "application/json")
	@ResponseStatus (value = HttpStatus.OK )
	public Story updateStory(@RequestBody CreateStoryRequest request, @PathVariable String id) throws Exception {
		Story resultStory = new Story();
		resultStory = resourcesService.updateStory(request, id);
		return resultStory;
	}
}