package com.resources.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class ResourcesController {

	@GetMapping("get")
	public String getting(){
		log.info("info log");
		log.warn("warn log ");
		log.error("error log");
		log.debug("debug log");
	return ("Hello at-resources-api"); 
	}
}