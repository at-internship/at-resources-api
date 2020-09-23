package com.resources.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ResourcesController {

	@GetMapping("get")
	public String getting(){
	return ("Hello at-resources-api"); 
	}
}