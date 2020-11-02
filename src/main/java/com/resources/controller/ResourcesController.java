package com.resources.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.resources.domain.CreateStoryResponse;
import com.resources.model.Story;
import com.resources.services.ResourcesService;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiParam;

@RequestMapping(value = "/at-resources-api/api/v1")
@RestController
@Slf4j
public class ResourcesController {

	@Autowired
	private ResourcesService resourcesService;

	@GetMapping("get")
	public String getting(){
		log.info("info log");
		log.warn("warn log ");
		log.error("error log");
		log.debug("debug log");
	return ("Hello at-resources-api");
	}

	@PostMapping(value = "/story")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CreateStoryResponse> postStories(
			@ApiParam(value = "Post story request", required = true) @RequestBody Story request) {
		CreateStoryResponse response = resourcesService.createStory(request);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}